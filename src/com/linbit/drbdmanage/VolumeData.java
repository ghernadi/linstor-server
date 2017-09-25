package com.linbit.drbdmanage;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.UUID;

import com.linbit.ImplementationError;
import com.linbit.TransactionMgr;
import com.linbit.drbdmanage.core.DrbdManage;
import com.linbit.drbdmanage.dbdrivers.interfaces.VolumeDataDatabaseDriver;
import com.linbit.drbdmanage.propscon.Props;
import com.linbit.drbdmanage.propscon.PropsAccess;
import com.linbit.drbdmanage.propscon.PropsContainer;
import com.linbit.drbdmanage.security.AccessContext;
import com.linbit.drbdmanage.security.AccessDeniedException;
import com.linbit.drbdmanage.security.AccessType;
import com.linbit.drbdmanage.security.ObjectProtection;
import com.linbit.drbdmanage.stateflags.StateFlags;
import com.linbit.drbdmanage.stateflags.StateFlagsBits;
import com.linbit.drbdmanage.stateflags.StateFlagsPersistence;

/**
 *
 * @author Robert Altnoeder &lt;robert.altnoeder@linbit.com&gt;
 */
public class VolumeData extends BaseTransactionObject implements Volume
{
    // Object identifier
    private final UUID objId;

    // Reference to the resource this volume belongs to
    private final Resource resourceRef;

    // Reference to the resource definition that defines the resource this volume belongs to
    private final ResourceDefinition resourceDfn;

    // Reference to the volume definition that defines this volume
    private final VolumeDefinition volumeDfn;

    // Properties container for this volume
    private final Props volumeProps;

    // State flags
    private final StateFlags<VlmFlags> flags;

    private final String blockDevicePath;

    private final String metaDiskPath;

    private final VolumeDataDatabaseDriver dbDriver;

    private boolean deleted = false;

    /*
     * used by getInstance
     */
    private VolumeData(
        Resource resRef,
        VolumeDefinition volDfn,
        String blockDevicePathRef,
        String metaDiskPathRef,
        long initFlags,
        AccessContext accCtx,
        TransactionMgr transMgr
    )
        throws SQLException, AccessDeniedException
    {
        this(
            UUID.randomUUID(),
            resRef,
            volDfn,
            blockDevicePathRef,
            metaDiskPathRef,
            initFlags,
            accCtx,
            transMgr
        );
    }

    /*
     * used by database drivers and tests
     */
    VolumeData(
        UUID uuid,
        Resource resRef,
        VolumeDefinition volDfnRef,
        String blockDevicePathRef,
        String metaDiskPathRef,
        long initFlags,
        AccessContext accCtx,
        TransactionMgr transMgr
    )
        throws SQLException, AccessDeniedException
    {
        objId = uuid;
        resourceRef = resRef;
        resourceDfn = resRef.getDefinition();
        volumeDfn = volDfnRef;
        blockDevicePath = blockDevicePathRef;
        metaDiskPath = metaDiskPathRef;

        dbDriver = DrbdManage.getVolumeDataDatabaseDriver();

        flags = new VlmFlagsImpl(
            resRef.getObjProt(),
            this,
            dbDriver.getStateFlagsPersistence(),
            initFlags
        );

        volumeProps = PropsContainer.getInstance(
            PropsContainer.buildPath(
                resRef.getAssignedNode().getName(),
                resRef.getDefinition().getName(),
                volDfnRef.getVolumeNumber(accCtx)
            ),
            transMgr
        );

        transObjs = Arrays.asList(
            resourceRef,
            volumeDfn,
            volumeProps,
            flags
        );
    }

    public static VolumeData getInstance(
        AccessContext accCtx,
        Resource resRef,
        VolumeDefinition volDfn,
        String blockDevicePathRef,
        String metaDiskPathRef,
        VlmFlags[] flags,
        TransactionMgr transMgr,
        boolean createIfNotExists
    )
        throws SQLException, AccessDeniedException
    {
        VolumeData vol = null;

        VolumeDataDatabaseDriver driver = DrbdManage.getVolumeDataDatabaseDriver();
        if (transMgr != null)
        {
            vol = driver.load(
                resRef,
                volDfn,
                transMgr
            );
        }

        if (vol == null && createIfNotExists)
        {
            long initFlags = StateFlagsBits.getMask(flags);

            vol = new VolumeData(
                resRef,
                volDfn,
                blockDevicePathRef,
                metaDiskPathRef,
                initFlags,
                accCtx,
                transMgr
            );
            if (transMgr != null)
            {
                driver.create(vol, transMgr);
            }
        }

        if (vol != null)
        {
            ((ResourceData) resRef).putVolume(accCtx, vol);

            vol.initialized();
        }
        return vol;
    }


    @Override
    public UUID getUuid()
    {
        checkDeleted();
        return objId;
    }

    @Override
    public Props getProps(AccessContext accCtx)
        throws AccessDeniedException
    {
        checkDeleted();
        return PropsAccess.secureGetProps(accCtx, resourceRef.getObjProt(), volumeProps);
    }

    @Override
    public Resource getResource()
    {
        checkDeleted();
        return resourceRef;
    }

    @Override
    public ResourceDefinition getResourceDefinition()
    {
        checkDeleted();
        return resourceDfn;
    }

    @Override
    public VolumeDefinition getVolumeDefinition()
    {
        checkDeleted();
        return volumeDfn;
    }

    @Override
    public StateFlags<VlmFlags> getFlags()
    {
        checkDeleted();
        return flags;
    }

    @Override
    public String getBlockDevicePath(AccessContext accCtx) throws AccessDeniedException
    {
        checkDeleted();
        resourceRef.getObjProt().requireAccess(accCtx, AccessType.VIEW);
        return blockDevicePath;
    }

    @Override
    public String getMetaDiskPath(AccessContext accCtx) throws AccessDeniedException
    {
        checkDeleted();
        resourceRef.getObjProt().requireAccess(accCtx, AccessType.VIEW);
        return metaDiskPath;
    }

    @Override
    public void delete(AccessContext accCtx)
        throws AccessDeniedException, SQLException
    {
        checkDeleted();
        // TODO: still a good idea that volume does not have its own objProt?
        resourceRef.getObjProt().requireAccess(accCtx, AccessType.USE);

        ((ResourceData) resourceRef).removeVolume(accCtx, this);
        dbDriver.delete(this, transMgr);
        deleted = true;
    }

    private void checkDeleted()
    {
        if (deleted)
        {
            throw new ImplementationError("Access to deleted node", null);
        }
    }

    private final class VlmFlagsImpl extends StateFlagsBits<VolumeData, VlmFlags>
    {
        VlmFlagsImpl(
            ObjectProtection objProtRef,
            VolumeData parent,
            StateFlagsPersistence<VolumeData> persistenceRef,
            long initFlags
        )
        {
            super(
                objProtRef,
                parent,
                StateFlagsBits.getMask(
                    VlmFlags.values()
                ),
                persistenceRef,
                initFlags
            );
        }
    }
}
