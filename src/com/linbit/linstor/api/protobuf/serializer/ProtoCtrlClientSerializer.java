package com.linbit.linstor.api.protobuf.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.linbit.linstor.Node;
import com.linbit.linstor.Resource;
import com.linbit.linstor.ResourceDefinition;
import com.linbit.linstor.StorPool;
import com.linbit.linstor.StorPoolDefinition;
import com.linbit.linstor.Node.NodeApi;
import com.linbit.linstor.Resource.RscApi;
import com.linbit.linstor.ResourceDefinition.RscDfnApi;
import com.linbit.linstor.StorPool.StorPoolApi;
import com.linbit.linstor.StorPoolDefinition.StorPoolDfnApi;
import com.linbit.linstor.api.AbsCtrlClientSerializer;
import com.linbit.linstor.api.pojo.ResourceState;
import com.linbit.linstor.core.Controller;
import com.linbit.linstor.logging.ErrorReporter;
import com.linbit.linstor.proto.MsgApiVersionOuterClass;
import com.linbit.linstor.proto.MsgHeaderOuterClass;
import com.linbit.linstor.proto.MsgLstNodeOuterClass;
import com.linbit.linstor.proto.MsgLstRscDfnOuterClass;
import com.linbit.linstor.proto.MsgLstRscOuterClass;
import com.linbit.linstor.proto.MsgLstStorPoolDfnOuterClass;
import com.linbit.linstor.proto.MsgLstStorPoolOuterClass;
import com.linbit.linstor.proto.apidata.NodeApiData;
import com.linbit.linstor.proto.apidata.RscApiData;
import com.linbit.linstor.proto.apidata.RscDfnApiData;
import com.linbit.linstor.proto.apidata.StorPoolApiData;
import com.linbit.linstor.proto.apidata.StorPoolDfnApiData;
import com.linbit.linstor.security.AccessContext;

public class ProtoCtrlClientSerializer extends AbsCtrlClientSerializer
{
    public ProtoCtrlClientSerializer(
        final ErrorReporter errReporterRef,
        final AccessContext serializerCtxRef
    )
    {
        super(errReporterRef, serializerCtxRef);
    }

    @Override
    public void writeHeader(String apiCall, int msgId, ByteArrayOutputStream baos) throws IOException
    {
        MsgHeaderOuterClass.MsgHeader.newBuilder()
            .setApiCall(apiCall)
            .setMsgId(msgId)
            .build()
            .writeDelimitedTo(baos);
    }

    @Override
    public void writeNodeList(List<NodeApi> nodes, ByteArrayOutputStream baos) throws IOException
    {
        MsgLstNodeOuterClass.MsgLstNode.Builder msgListNodeBuilder = MsgLstNodeOuterClass.MsgLstNode.newBuilder();

        for (Node.NodeApi apiNode: nodes)
        {
            msgListNodeBuilder.addNodes(NodeApiData.toNodeProto(apiNode));
        }

        msgListNodeBuilder.build().writeDelimitedTo(baos);
    }

    @Override
    public void writeStorPoolDfnList(List<StorPoolDfnApi> storPoolDfns, ByteArrayOutputStream baos)
        throws IOException
    {
        MsgLstStorPoolDfnOuterClass.MsgLstStorPoolDfn.Builder msgListStorPoolDfnsBuilder =
            MsgLstStorPoolDfnOuterClass.MsgLstStorPoolDfn.newBuilder();

        for (StorPoolDefinition.StorPoolDfnApi apiDfn: storPoolDfns)
        {
            msgListStorPoolDfnsBuilder.addStorPoolDfns(StorPoolDfnApiData.fromStorPoolDfnApi(apiDfn));
        }

        msgListStorPoolDfnsBuilder.build().writeDelimitedTo(baos);
    }

    @Override
    public void writeStorPoolList(List<StorPoolApi> storPools, ByteArrayOutputStream baos)
        throws IOException
    {
        MsgLstStorPoolOuterClass.MsgLstStorPool.Builder msgListBuilder =
            MsgLstStorPoolOuterClass.MsgLstStorPool.newBuilder();
        for (StorPool.StorPoolApi apiStorPool: storPools)
        {
            msgListBuilder.addStorPools(StorPoolApiData.toStorPoolProto(apiStorPool));
        }

        msgListBuilder.build().writeDelimitedTo(baos);
    }

    @Override
    public void writeRscDfnList(List<RscDfnApi> rscDfns, ByteArrayOutputStream baos) throws IOException
    {
        MsgLstRscDfnOuterClass.MsgLstRscDfn.Builder msgListRscDfnsBuilder = MsgLstRscDfnOuterClass.MsgLstRscDfn.newBuilder();

        for (ResourceDefinition.RscDfnApi apiRscDfn: rscDfns)
        {
            msgListRscDfnsBuilder.addRscDfns(RscDfnApiData.fromRscDfnApi(apiRscDfn));
        }

        msgListRscDfnsBuilder.build().writeDelimitedTo(baos);
    }

    @Override
    public void writeRscList(List<RscApi> rscs, Collection<ResourceState> rscStates, ByteArrayOutputStream baos)
        throws IOException
    {
        MsgLstRscOuterClass.MsgLstRsc.Builder msgListRscsBuilder = MsgLstRscOuterClass.MsgLstRsc.newBuilder();

        for (Resource.RscApi apiRsc: rscs)
        {
            msgListRscsBuilder.addResources(RscApiData.toRscProto(apiRsc));
        }

        for (ResourceState rscState : rscStates)
        {
            msgListRscsBuilder.addResourceStates(
                ProtoCommonSerializer.buildResourceState(
                    rscState.getNodeName(),
                    rscState
                )
            );
        }

        msgListRscsBuilder.build().writeDelimitedTo(baos);
    }

    @Override
    public void writeApiVersion(
        final long features,
        final String controllerInfo,
        ByteArrayOutputStream baos) throws IOException
    {
        MsgApiVersionOuterClass.MsgApiVersion.Builder msgApiVersion = MsgApiVersionOuterClass.MsgApiVersion.newBuilder();

        // set features
        msgApiVersion.setVersion(Controller.API_VERSION);
        msgApiVersion.setFeatures(features);
        msgApiVersion.setControlerInfo(controllerInfo);
        msgApiVersion.build().writeDelimitedTo(baos);
    }
}
