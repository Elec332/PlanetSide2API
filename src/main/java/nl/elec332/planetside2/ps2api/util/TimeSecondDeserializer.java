package nl.elec332.planetside2.ps2api.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.Instant;

/**
 * Created by Elec332 on 24/04/2021
 */
final class TimeSecondDeserializer implements JsonDeserializer<Instant>, JsonSerializer<Instant> {

    @Override
    public Instant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return Instant.ofEpochSecond(json.getAsLong());
    }

    @Override
    public JsonElement serialize(Instant src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getEpochSecond());
    }

}
