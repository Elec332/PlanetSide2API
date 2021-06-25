package nl.elec332.planetside2.ps2api.api.streaming;

import nl.elec332.planetside2.ps2api.api.objects.world.IServer;

/**
 * Created by Elec332 on 01/05/2021
 */
public interface IHeartBeatMessage {

    default boolean getServerStatus(IServer server) {
        return getServerStatus((int) server.getId());
    }

    boolean getServerStatus(int serverId);

}
