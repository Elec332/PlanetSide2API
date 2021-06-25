package nl.elec332.planetside2.ps2api.util;

import com.google.gson.*;
import nl.elec332.planetside2.ps2api.api.streaming.event.base.IStreamingEvent;
import nl.elec332.planetside2.ps2api.api.streaming.request.IStreamingEventType;
import nl.elec332.planetside2.ps2api.impl.streaming.request.EventServiceFactory;

import java.lang.reflect.Type;

/**
 * Created by Elec332 on 05/05/2021
 */
final class StreamingEventDeserializer implements JsonDeserializer<IStreamingEvent> {

    private static final Gson GSON = new Gson();

    @Override
    public IStreamingEvent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject o = json.getAsJsonObject();
        String name;
        if (o.has("event_name")) {
            name = o.get("event_name").getAsString();
        } else {
            name = o.get("event_type").getAsString();
        }
        IStreamingEventType<?> type = EventServiceFactory.INSTANCE.getByName(name);
        if (type == null) {
            throw new RuntimeException("Event name unsupported: " + name);
        }
        return NetworkUtil.GSON.fromJson(json, EventServiceFactory.getImplementation(type.getEventType()));
    }

}
