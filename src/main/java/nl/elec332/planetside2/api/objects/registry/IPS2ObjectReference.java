package nl.elec332.planetside2.api.objects.registry;

/**
 * Created by Elec332 on 24/04/2021
 */
public interface IPS2ObjectReference<T extends IPS2Object> {

    long getId();

    T getObject();

    Class<T> getType();

}
