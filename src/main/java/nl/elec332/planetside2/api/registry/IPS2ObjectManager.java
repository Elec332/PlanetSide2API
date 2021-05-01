package nl.elec332.planetside2.api.registry;

/**
 * Created by Elec332 on 24/04/2021
 */
public interface IPS2ObjectManager<T extends IPS2Object> {

    T getCached(long id);

    T get(long id);

    IPS2ObjectReference<T> getReference(long id);

    Class<T> getType();

}
