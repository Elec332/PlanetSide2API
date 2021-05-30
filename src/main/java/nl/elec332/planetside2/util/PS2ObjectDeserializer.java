package nl.elec332.planetside2.util;

import com.google.gson.*;
import nl.elec332.planetside2.api.objects.registry.IPS2Object;
import nl.elec332.planetside2.api.objects.registry.IPS2ObjectManager;
import nl.elec332.planetside2.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.impl.PS2APIAccessor;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/**
 * Created by Elec332 on 24/04/2021
 */
final class PS2ObjectDeserializer implements JsonDeserializer<IPS2ObjectReference<IPS2Object>>, JsonSerializer<IPS2ObjectReference<IPS2Object>> {

    @Override
    @SuppressWarnings("unchecked")
    public IPS2ObjectReference<IPS2Object> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        IPS2ObjectManager<IPS2Object> m;
        if (typeOfT instanceof ParameterizedType) {
            Type t = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
            if (t instanceof Class) {
                m = PS2APIAccessor.getManagerEarly((Class<IPS2Object>) t);
            } else if (t instanceof WildcardType) {
                m = null;
                for (Type t2  : ((WildcardType) t).getLowerBounds()) {
                    if (t2 instanceof Class) {
                        m = PS2APIAccessor.getManagerEarly((Class<IPS2Object>) t2);
                        break;
                    }
                }
                if (m == null) {
                    for (Type t2  : ((WildcardType) t).getUpperBounds()) {
                        if (t2 instanceof Class) {
                            m = PS2APIAccessor.getManagerEarly((Class<IPS2Object>) t2);
                        }
                    }
                }
                if (m == null) {
                    throw new UnsupportedOperationException("Unable to convert bounds");
                }
            } else {
                throw new UnsupportedOperationException(t.getClass() + "  " + t.getTypeName());
            }
        } else {
            throw new UnsupportedOperationException();
        }
        return m.getReference(json.getAsLong());
    }

    @Override
    public JsonElement serialize(IPS2ObjectReference<IPS2Object> src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getId());
    }

}
