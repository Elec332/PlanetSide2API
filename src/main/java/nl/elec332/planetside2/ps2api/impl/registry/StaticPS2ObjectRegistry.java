package nl.elec332.planetside2.ps2api.impl.registry;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import nl.elec332.planetside2.ps2api.api.ICensusAPI;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2Object;
import nl.elec332.planetside2.ps2api.util.Constants;
import nl.elec332.planetside2.ps2api.util.NetworkUtil;

import java.util.Map;
import java.util.function.Function;

/**
 * Created by Elec332 on 23/04/2021
 */
public class StaticPS2ObjectRegistry<T extends IPS2Object> extends AbstractPS2ObjectRegistry<T> {

    public StaticPS2ObjectRegistry(ICensusAPI api, Class<T> type, String typeName) {
        this(api, type, typeName, (String) null);
    }

    public StaticPS2ObjectRegistry(ICensusAPI api, Class<T> type, String typeName, String request) {
        this(api, type, typeName, request, o -> NetworkUtil.GSON.fromJson(o, type));
    }

    public StaticPS2ObjectRegistry(ICensusAPI api, Class<T> type, String typeName, Function<JsonObject, T> deserializer) {
        this(api, type, typeName, null, deserializer);
    }

    public StaticPS2ObjectRegistry(ICensusAPI api, Class<T> type, String typeName, String request, Function<JsonObject, T> deserializer) {
        super(type);
        this.api = api;
        this.type = typeName;
        this.request = "c:lang=en" + (request == null ? "" : "&" + request) + "&c:limit=10000";
        this.deserializer = deserializer;
    }

    private final ICensusAPI api;
    private final String type;
    private final String request;
    private final Function<JsonObject, T> deserializer;

    public void update() {
        updateMap(newMap -> fetchItems(newMap, null));
    }

    protected void fetchItems(Map<Long, T> map, String preRequest) {
        if (preRequest == null) {
            preRequest = "";
        }
        if (!preRequest.isEmpty()) {
            preRequest = preRequest + "&";
        }
        int index = 0;
        while (index >= 0) {
            JsonArray objects = api.invokeAPI(type, preRequest + "c:limit=" + Constants.API_SAFE_RETURN_SIZE + "&" + request + "&c:start=" + index);
            for (int i = 0; i < objects.size(); i++) {
                JsonObject jo = objects.get(i).getAsJsonObject();
                T obj = deserializer.apply(jo);
                map.put(obj.getId(), obj);
            }
            if (objects.size() >= Constants.API_SAFE_RETURN_SIZE) {
                index += (Constants.API_SAFE_RETURN_SIZE - 50);
            } else {
                index = -1;
            }
        }
    }

}
