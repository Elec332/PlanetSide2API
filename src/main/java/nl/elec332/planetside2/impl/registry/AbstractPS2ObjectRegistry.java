package nl.elec332.planetside2.impl.registry;

import nl.elec332.planetside2.api.registry.IPS2Object;
import nl.elec332.planetside2.api.registry.IPS2ObjectReference;
import nl.elec332.planetside2.api.registry.IPS2ObjectRegistry;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.ObjLongConsumer;
import java.util.stream.Stream;

/**
 * Created by Elec332 on 23/04/2021
 */
public abstract class AbstractPS2ObjectRegistry<T extends IPS2Object> extends AbstractPS2ObjectManager<T> implements IPS2ObjectRegistry<T> {

    protected AbstractPS2ObjectRegistry(Class<T> type) {
        super(type);
        this.objectRefs = new TreeMap<>();
        this.objectNames = new HashMap<>();
    }

    private final Map<Long, CachedRef> objectRefs;
    private final Map<String, T> objectNames;
    private Map<Long, T> objects;
    private BiConsumer<T, T> changeListener = (a, b) -> {
    };

    protected void setChangeListener(BiConsumer<T, T> changeListener) {
        this.changeListener = changeListener;
    }

    protected void updateMap(Consumer<Map<Long, T>> newMap) {
        Map<Long, T> oldMap = this.objects;
        this.objects = new TreeMap<>();
        newMap.accept(this.objects);

        this.objectRefs.values().forEach(CachedRef::clear);
        this.objects.forEach((i, t) -> this.objectRefs.computeIfAbsent(i, CachedRef::new));

        this.objectNames.clear();
        this.objects.values().forEach(o -> this.objectNames.put(o.getName().toLowerCase(Locale.ROOT), o));

        if (oldMap == null) {
            return;
        }

        this.objects.forEach((i, t) -> this.changeListener.accept(oldMap.remove(i), t));
        oldMap.forEach((i, t) -> {
            this.changeListener.accept(t, null);
            this.objectRefs.remove(t.getId());
        });
    }

    @Override
    public T getByName(String name) {
        return this.objectNames.get(name.toLowerCase(Locale.ROOT));
    }

    @Override
    public T getCached(long id) {
        return objects.get(id);
    }

    @Override
    public IPS2ObjectReference<T> getReference(long id) {
        return this.objectRefs.get(id);
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Collection<IPS2ObjectReference<T>> getObjects() {
        return (Collection) objectRefs.values();
    }

    @Override
    public Stream<T> stream() {
        return objects.values().stream();
    }

    @Override
    public void forEach(ObjLongConsumer<T> c) {
        objects.forEach((i, t) -> c.accept(t, i));
    }

}
