package nl.elec332.planetside2.ps2api.api.objects.player;

import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2Object;

/**
 * Created by Elec332 on 27/04/2021
 */
public interface IExperienceType extends IPS2Object {

    @Override
    long getId();

    @Override
    String getName();

    int getXp();

}
