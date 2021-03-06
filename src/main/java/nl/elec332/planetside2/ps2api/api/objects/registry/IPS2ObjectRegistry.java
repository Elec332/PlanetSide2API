package nl.elec332.planetside2.ps2api.api.objects.registry;

import java.util.Collection;
import java.util.function.ObjLongConsumer;
import java.util.stream.Stream;

/**
 * Created by Elec332 on 23/04/2021
 */
public interface IPS2ObjectRegistry<T extends IPS2Object> extends IPS2ObjectManager<T> {

    @Override
    default T get(long id) {
        return getCached(id);
    }

    @Override
    T getByName(String name);

    @Override
    T getCached(long id);

    @Override
    IPS2ObjectReference<T> getReference(long id);

    Collection<IPS2ObjectReference<T>> getObjects();

    Stream<T> stream();

    void forEach(ObjLongConsumer<T> c);

    @Override
    Class<T> getType();

}
