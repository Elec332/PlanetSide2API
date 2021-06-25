package nl.elec332.planetside2.ps2api.impl.streaming;

import com.google.gson.JsonObject;
import nl.elec332.planetside2.ps2api.api.streaming.IHeartBeatMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Elec332 on 01/05/2021
 */
public class HeartBeatEvent implements IHeartBeatMessage {

    public HeartBeatEvent(JsonObject online) {
        this.status = new HashMap<>();
        for (String s : online.keySet()) {
            String id = s.replace("EventServerEndpoint_", "").split("_")[1];
            this.status.put(Integer.parseInt(id), online.get(s).getAsBoolean());
        }
    }

    private final Map<Integer, Boolean> status;

    @Override
    public boolean getServerStatus(int serverId) {
        return this.status.get(serverId);
    }

}
