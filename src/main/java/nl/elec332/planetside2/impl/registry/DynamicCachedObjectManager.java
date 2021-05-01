package nl.elec332.planetside2.impl.registry;

import nl.elec332.planetside2.api.ICensusAPI;
import nl.elec332.planetside2.api.registry.IPS2Object;
import nl.elec332.planetside2.api.registry.IPS2ObjectReference;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

/**
 * Created by Elec332 on 24/04/2021
 */
public class DynamicCachedObjectManager<T extends IPS2Object> extends AbstractDynamicObjectManager<T> {

    public DynamicCachedObjectManager(ICensusAPI api, String prefix, Class<T> type, String otherArgs) {
        super(api, prefix, type, otherArgs);
        this.objectRefs = new TreeMap<>();
    }

    private final Map<Long, CachedRef> objectRefs;
    private Map<Long, T> objects;

    public void update() {
        updateMap(m -> {
            for (long l : objects.keySet()) {
                m.put(l, getDirectly(l));
            }
        });
    }

    protected void updateMap(Consumer<Map<Long, T>> newMap) {
        Map<Long, T> oldMap = this.objects;
        this.objects = new TreeMap<>();
        newMap.accept(this.objects);

        this.objectRefs.values().forEach(CachedRef::clear);

        if (oldMap == null) {
            return;
        }
        this.objects.keySet().forEach(oldMap::remove);
        oldMap.forEach((i, t) -> this.objectRefs.remove(t.getId()));
    }

    @Override
    public T getCached(long id) {
        return objects.computeIfAbsent(id, this::get);
    }

    @Override
    public IPS2ObjectReference<T> getReference(long id) {
        return this.objectRefs.computeIfAbsent(id, CachedRef::new);
    }

}
