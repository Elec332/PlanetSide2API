package nl.elec332.planetside2.impl.registry;

import com.google.gson.JsonObject;
import nl.elec332.planetside2.api.ICensusAPI;
import nl.elec332.planetside2.api.registry.IPS2Object;
import nl.elec332.planetside2.api.registry.IPS2ObjectReference;
import nl.elec332.planetside2.util.NetworkUtil;

/**
 * Created by Elec332 on 24/04/2021
 */
public abstract class AbstractDynamicObjectManager<T extends IPS2Object> extends AbstractPS2ObjectManager<T> {

    public AbstractDynamicObjectManager(ICensusAPI api, String prefix, Class<T> type, String otherArgs) {
        super(type);
        this.api = api;
        this.prefix = prefix;
        this.otherArgs = otherArgs;
    }

    private final ICensusAPI api;
    private final String prefix;
    private final String otherArgs;

    protected T getDirectly(long id) {
        JsonObject o = api.requestSingleObject(prefix, prefix + "_id=" + id + (otherArgs == null ? "" : "&" + otherArgs));
        return NetworkUtil.GSON.fromJson(o, getType());
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
