package nl.elec332.planetside2.api.objects.misc;

import nl.elec332.planetside2.api.objects.registry.IPS2Object;

/**
 * Created by Elec332 on 05/05/2021
 */
public interface IMetaGameEventState extends IPS2Object {

    @Override
    long getId();

    @Override
    String getName();

}
