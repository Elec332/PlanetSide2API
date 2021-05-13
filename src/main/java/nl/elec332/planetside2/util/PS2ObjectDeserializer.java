package nl.elec332.planetside2.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import nl.elec332.planetside2.api.objects.registry.IPS2Object;
import nl.elec332.planetside2.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.impl.PS2APIAccessor;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Elec332 on 24/04/2021
 */
final class PS2ObjectDeserializer implements JsonDeserializer<IPS2ObjectReference<IPS2Object>> {

    @Override
    @SuppressWarnings("unchecked")
    public IPS2ObjectReference<IPS2Object> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Class<?> type;
        if (typeOfT instanceof ParameterizedType) {
            type = (Class<?>) ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
        } else {
            throw new UnsupportedOperationException();
        }
        return PS2APIAccessor.getManagerEarly((Class<IPS2Object>) type).getReference(json.getAsLong());
    }

}
