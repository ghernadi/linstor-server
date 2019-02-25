package com.linbit.linstor.api.rest.v1.serializer;

import com.linbit.linstor.NetInterface;
import com.linbit.linstor.ResourceConnection;
import com.linbit.linstor.SnapshotDefinition;
import com.linbit.linstor.SnapshotVolumeDefinition;
import com.linbit.linstor.VolumeDefinition;
import com.linbit.linstor.api.interfaces.AutoSelectFilterApi;
import com.linbit.linstor.api.pojo.NetInterfacePojo;
import com.linbit.linstor.api.pojo.VlmDfnPojo;
import com.linbit.linstor.stateflags.FlagsHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Json
{
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class NetInterfaceData
    {
        public String name;
        public String address;
        public Integer stlt_port;
        public String stlt_encryption_type;

        public NetInterfaceData()
        {
        }

        public NetInterfaceData(NetInterface.NetInterfaceApi netIfApi)
        {
            name = netIfApi.getName();
            address = netIfApi.getAddress();
            if (netIfApi.isUsableAsSatelliteConnection())
            {
                stlt_encryption_type = netIfApi.getSatelliteConnectionEncryptionType();
                stlt_port = netIfApi.getSatelliteConnectionPort();
            }
        }

        public NetInterface.NetInterfaceApi toApi()
        {
            return new NetInterfacePojo(null, name, address, stlt_port, stlt_encryption_type);
        }
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class NodeData
    {
        public String name;
        public String type;
        public Map<String, String> props;
        public List<String> flags;
        public List<NetInterfaceData> net_interfaces = new ArrayList<>();
        public String connection_status;
    }

    public static class NodeModifyData
    {
        public String node_type;
        public Map<String, String> override_props = Collections.emptyMap();
        public Set<String> delete_props = Collections.emptySet();
        public Set<String> delete_namespaces = Collections.emptySet();
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class StorPoolData
    {
        public String node_name;
        public String storage_pool_name;
        public String driver;
        public Map<String, String> props = new HashMap<>();
        // Volumes are for now not reported, maybe later via flag
        public Map<String, String> static_traits;
        public Long free_capacity;
        public Long total_capacity;
        public String free_space_mgr_name;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class StorPoolModifyData
    {
        public Map<String, String> override_props = Collections.emptyMap();
        public Set<String> delete_props = Collections.emptySet();
        public Set<String> delete_namespaces = Collections.emptySet();
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class ResourceDefinitionData
    {
        public String name;
        public Integer port;
        public String secret;
        public Map<String, String> props;
        public List<String> flags;
        public boolean is_down;
        public List<VolumeDefinitionData> volume_definitions;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class ResourceDefinitionModifyData
    {
        public Integer port;
        public Map<String, String> override_props = Collections.emptyMap();
        public Set<String> delete_props = Collections.emptySet();
        public Set<String> delete_namespaces = Collections.emptySet();
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class VolumeDefinitionData
    {
        public Integer volume_number;
        public Long size;
        public Integer minor_number;
        public Map<String, String> props = Collections.emptyMap();
        public List<String> flags = Collections.emptyList();

        public VolumeDefinition.VlmDfnApi toVlmDfnApi()
        {
            return new VlmDfnPojo(
                null,
                volume_number,
                minor_number,
                size,
                FlagsHelper.fromStringList(VolumeDefinition.VlmDfnFlags.class, flags),
                props
            );
        }
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class VolumeDefinitionModifyData
    {
        public Long size;
        public Integer minor_number;
        public Map<String, String> override_props = Collections.emptyMap();
        public Set<String> delete_props = Collections.emptySet();
        public Set<String> delete_namespaces = Collections.emptySet();
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class ResourceData
    {
        public String name;
        public String node_name;
        public Map<String, String> props = Collections.emptyMap();
        public List<String> flags = Collections.emptyList();
        public Integer node_id;
        public Boolean override_node_id;
        public ResourceStateData state;
    }

    public static class ResourceStateData
    {
        public Boolean in_use;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class ResourceModifyData
    {
        public Map<String, String> override_props = Collections.emptyMap();
        public Set<String> delete_props = Collections.emptySet();
        public Set<String> delete_namespaces = Collections.emptySet();
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class AutoSelectFilterData implements AutoSelectFilterApi
    {
        public int place_count = 2;
        public String storage_pool;
        public List<String> not_place_with_rsc = new ArrayList<>();
        public String not_place_with_rsc_regex;
        public List<String> replicas_on_same = new ArrayList<>();
        public List<String> replicas_on_different = new ArrayList<>();

        @Override
        public int getPlaceCount()
        {
            return place_count;
        }

        @Override
        public String getStorPoolNameStr()
        {
            return storage_pool;
        }

        @Override
        public List<String> getNotPlaceWithRscList()
        {
            return not_place_with_rsc;
        }

        @Override
        public String getNotPlaceWithRscRegex()
        {
            return not_place_with_rsc_regex;
        }

        @Override
        public List<String> getReplicasOnSameList()
        {
            return replicas_on_same;
        }

        @Override
        public List<String> getReplicasOnDifferentList()
        {
            return replicas_on_different;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class AutoPlaceRequest
    {
        public boolean diskless_on_remaining = false;
        public AutoSelectFilterData select_filter = new AutoSelectFilterData();
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class ErrorReport
    {
        public String node_name;
        public long error_time;
        public String filename;
        public String text;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class ControllerPropsModify
    {
        public Map<String, String> override_props = Collections.emptyMap();
        public Set<String> delete_props = Collections.emptySet();
        public Set<String> delete_namespaces = Collections.emptySet();
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class ControllerVersion
    {
        public String version;
        public String git_hash;
        public String build_time;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class PassPhraseCreate
    {
        public String new_passphrase;
        public String old_passphrase;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class SnapshotVolumeDfnData
    {
        public Integer volume_number;
        public long size;

        public SnapshotVolumeDfnData()
        {
        }

        public SnapshotVolumeDfnData(SnapshotVolumeDefinition.SnapshotVlmDfnApi snapshotVlmDfnApi)
        {
            volume_number = snapshotVlmDfnApi.getVolumeNr();
            size = snapshotVlmDfnApi.getSize();
        }
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class SnapshotData
    {
        public String name;
        public String resource_name;
        public List<String> nodes = Collections.emptyList();
        public Map<String, String> props = Collections.emptyMap();
        public List<String> flags = Collections.emptyList();
        public List<SnapshotVolumeDfnData> volume_definitions = Collections.emptyList();

        public SnapshotData()
        {
        }

        public SnapshotData(SnapshotDefinition.SnapshotDfnListItemApi snapshotDfnListItemApi)
        {
            name = snapshotDfnListItemApi.getSnapshotName();
            resource_name = snapshotDfnListItemApi.getRscDfn().getResourceName();
            nodes = snapshotDfnListItemApi.getNodeNames();
            props = snapshotDfnListItemApi.getProps();
            flags = FlagsHelper.toStringList(
                SnapshotDefinition.SnapshotDfnFlags.class, snapshotDfnListItemApi.getFlags()
            );
            volume_definitions = snapshotDfnListItemApi.getSnapshotVlmDfnList().stream()
                .map(SnapshotVolumeDfnData::new)
                .collect(Collectors.toList());
        }
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class SnapshotRestore
    {
        public List<String> nodes = Collections.emptyList();  //optional
        public String to_resource;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class ResourceConnectionData
    {
        public String node_a;
        public String node_b;
        public Map<String, String> props = Collections.emptyMap();
        public List<String> flags = Collections.emptyList();
        public Integer port;

        public ResourceConnectionData()
        {
        }

        public ResourceConnectionData(ResourceConnection.RscConnApi rscConnApi)
        {
            node_a = rscConnApi.getSourceNodeName();
            node_b = rscConnApi.getTargetNodeName();
            props = rscConnApi.getProps();
            flags = FlagsHelper.toStringList(ResourceConnection.RscConnFlags.class, rscConnApi.getFlags());
            port = rscConnApi.getPort();
        }
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class ResourceConnectionModify
    {
        public Map<String, String> override_props = Collections.emptyMap();
        public Set<String> delete_props = Collections.emptySet();
        public Set<String> delete_namespaces = Collections.emptySet();
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class DrbdProxyEnable
    {
        public Integer port;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class DrbdProxyModify
    {
        public Map<String, String> override_props = Collections.emptyMap();
        public Set<String> delete_props = Collections.emptySet();
        public Set<String> delete_namespaces = Collections.emptySet();
        public String compression_type;
        public Map<String, String> compression_props = Collections.emptyMap();
    }
}
