package nl.elec332.planetside2.impl.registry;

import nl.elec332.planetside2.api.ICensusAPI;
import nl.elec332.planetside2.api.objects.registry.IPS2Object;
import nl.elec332.planetside2.api.objects.registry.IPS2ObjectReference;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by Elec332 on 24/04/2021
 */
public class DynamicCachedObjectManager<T extends IPS2Object> extends AbstractDynamicObjectManager<T> {

    public DynamicCachedObjectManager(ICensusAPI api, String prefix, Class<T> type, String otherArgs, String... nameFields) {
        super(api, prefix, type, otherArgs, nameFields);
        this.objectRefs = new TreeMap<>();
        this.cacheCheck = a -> true;
    }

    private final Map<Long, CachedRef> objectRefs;
    private Map<Long, T> objects;
    private Predicate<T> cacheCheck;

    public DynamicCachedObjectManager<T> setCacheCheck(Predicate<T> test) {
        this.cacheCheck = test;
        return this;
    }

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
        if (id == 0) {
            return null;
        }
        T ret = this.objects.get(id);
        if (ret == null) {
            ret = get(id);
            if (ret != null && cacheCheck.test(ret)) {
                this.objects.put(id, ret);
            }
        }
        return ret;
    }

    @Override
    public IPS2ObjectReference<T> getReference(long id) {
        return this.objectRefs.computeIfAbsent(id, CachedRef::new);
    }

}
