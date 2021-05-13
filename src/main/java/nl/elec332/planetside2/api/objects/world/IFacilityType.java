package nl.elec332.planetside2.api.objects.world;

import nl.elec332.planetside2.api.objects.registry.IPS2Object;

/**
 * Created by Elec332 on 23/04/2021
 */
public interface IFacilityType extends IPS2Object {

    @Override
    long getId();

    @Override
    String getName();
}
