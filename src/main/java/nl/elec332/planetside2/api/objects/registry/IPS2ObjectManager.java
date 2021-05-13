package nl.elec332.planetside2.api.objects.registry;

/**
 * Created by Elec332 on 24/04/2021
 */
public interface IPS2ObjectManager<T extends IPS2Object> {

    T getCached(long id);

    T get(long id);

    T getByName(String name);

    IPS2ObjectReference<T> getReference(long id);

    Class<T> getType();

}
