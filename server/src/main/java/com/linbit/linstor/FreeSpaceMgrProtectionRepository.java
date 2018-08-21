package com.linbit.linstor;

import com.linbit.linstor.core.CoreModule;
import com.linbit.linstor.security.AccessContext;
import com.linbit.linstor.security.AccessDeniedException;
import com.linbit.linstor.security.AccessType;
import com.linbit.linstor.security.ObjectProtection;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Holds the singleton free space manager map protection instance, allowing it to be initialized from the database
 * after dependency injection has been performed.
 */
@Singleton
public class FreeSpaceMgrProtectionRepository implements FreeSpaceMgrRepository
{
    private final CoreModule.FreeSpaceMgrMap freeSpaceMgrMap;
    private ObjectProtection freeSpaceMgrMapObjProt;

    @Inject
    public FreeSpaceMgrProtectionRepository(CoreModule.FreeSpaceMgrMap freeSpaceMgrMapRef)
    {
        freeSpaceMgrMap = freeSpaceMgrMapRef;
    }

    public void setObjectProtection(ObjectProtection freeSpaceMgrMapObjProtRef)
    {
        if (freeSpaceMgrMapObjProt != null)
        {
            throw new IllegalStateException("Object protection already set");
        }
        freeSpaceMgrMapObjProt = freeSpaceMgrMapObjProtRef;
    }

    @Override
    public ObjectProtection getObjProt()
    {
        checkProtSet();
        return freeSpaceMgrMapObjProt;
    }

    @Override
    public void requireAccess(AccessContext accCtx, AccessType requested)
        throws AccessDeniedException
    {
        checkProtSet();
        freeSpaceMgrMapObjProt.requireAccess(accCtx, requested);
    }

    @Override
    public FreeSpaceMgr get(
        AccessContext accCtx,
        FreeSpaceMgrName freeSpaceMgrName
    )
        throws AccessDeniedException
    {
        checkProtSet();
        freeSpaceMgrMapObjProt.requireAccess(accCtx, AccessType.VIEW);
        return freeSpaceMgrMap.get(freeSpaceMgrName);
    }

    @Override
    public void put(AccessContext accCtx, FreeSpaceMgrName freeSpaceMgrName, FreeSpaceMgr freeSpaceMgr)
        throws AccessDeniedException
    {
        checkProtSet();
        freeSpaceMgrMapObjProt.requireAccess(accCtx, AccessType.CHANGE);
        freeSpaceMgrMap.put(freeSpaceMgrName, freeSpaceMgr);
    }

    @Override
    public void remove(AccessContext accCtx, FreeSpaceMgrName freeSpaceMgrName)
        throws AccessDeniedException
    {
        checkProtSet();
        freeSpaceMgrMapObjProt.requireAccess(accCtx, AccessType.CHANGE);
        freeSpaceMgrMap.remove(freeSpaceMgrName);
    }

    @Override
    public CoreModule.FreeSpaceMgrMap getMapForView(AccessContext accCtx)
        throws AccessDeniedException
    {
        checkProtSet();
        freeSpaceMgrMapObjProt.requireAccess(accCtx, AccessType.VIEW);
        return freeSpaceMgrMap;
    }

    private void checkProtSet()
    {
        if (freeSpaceMgrMapObjProt == null)
        {
            throw new IllegalStateException("Object protection not yet set");
        }
    }
}
