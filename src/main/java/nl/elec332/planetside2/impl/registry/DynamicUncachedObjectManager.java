package nl.elec332.planetside2.impl.registry;

import nl.elec332.planetside2.api.ICensusAPI;
import nl.elec332.planetside2.api.registry.IPS2Object;

/**
 * Created by Elec332 on 24/04/2021
 */
public class DynamicUncachedObjectManager<T extends IPS2Object> extends AbstractDynamicObjectManager<T> {

    public DynamicUncachedObjectManager(ICensusAPI api, String prefix, Class<T> type) {
        this(api, prefix, type, null);
    }

    public DynamicUncachedObjectManager(ICensusAPI api, String prefix, Class<T> type, String otherArgs) {
        super(api, prefix, type, otherArgs);
    }

    @Override
    public T getCached(long id) {
        return get(id);
    }

}
