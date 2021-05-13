package nl.elec332.planetside2.api.objects.registry;

import nl.elec332.planetside2.api.objects.weapons.IItem;

import java.util.Collection;
import java.util.function.ObjLongConsumer;
import java.util.stream.Stream;

/**
 * Created by Elec332 on 27/04/2021
 */
public interface IPS2ItemRegistry<I extends IItem> extends IPS2ObjectRegistry<I> {

    @Override
    I getByName(String name);

    @Override
    I getCached(long id);

    @Override
    IPS2ObjectReference<I> getReference(long id);

    @Override
    Collection<IPS2ObjectReference<I>> getObjects();

    @Override
    Stream<I> stream();

    @Override
    void forEach(ObjLongConsumer<I> c);

    @Override
    Class<I> getType();

}
