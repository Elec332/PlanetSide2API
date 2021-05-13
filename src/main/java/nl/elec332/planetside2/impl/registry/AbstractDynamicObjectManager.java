package nl.elec332.planetside2.impl.registry;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import nl.elec332.planetside2.api.ICensusAPI;
import nl.elec332.planetside2.api.objects.registry.IPS2Object;
import nl.elec332.planetside2.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.util.NetworkUtil;

import java.util.Locale;

/**
 * Created by Elec332 on 24/04/2021
 */
public abstract class AbstractDynamicObjectManager<T extends IPS2Object> extends AbstractPS2ObjectManager<T> {

    public AbstractDynamicObjectManager(ICensusAPI api, String prefix, Class<T> type, String otherArgs, String... nameFields) {
        super(type);
        this.api = api;
        this.prefix = prefix;
        this.otherArgs = otherArgs;
        this.nameFields = nameFields;
    }

    private final ICensusAPI api;
    private final String prefix;
    private final String otherArgs;
    private final String[] nameFields;

    protected T getDirectly(long id) {
        if (id == 0) {
            return null;
        }
        JsonObject o = api.requestSingleObject(prefix, prefix + "_id=" + id + (otherArgs == null ? "" : "&" + otherArgs));
        return NetworkUtil.GSON.fromJson(o, getType());
    }

    @Override
    public T getByName(String name) {
        name = name.toLowerCase(Locale.ROOT);
        for (String nf : nameFields) {
            JsonArray a = api.invokeAPI(prefix, nf + "=" + name + "&c:show=" + prefix + "_id");
            if (a.size() == 1) {
                //long id = a.get(0).getAsJsonObject().get(prefix + "_id").getAsLong(); // <- gets squashed to a long array
                long id = a.get(0).getAsLong();
                return get(id);
            }
        }
        return null;
    }

    @Override
    public T get(long id) {
        return getDirectly(id);
    }

    @Override
    public IPS2ObjectReference<T> getReference(long id) {
        return new DynamicRef(id);
    }

}
