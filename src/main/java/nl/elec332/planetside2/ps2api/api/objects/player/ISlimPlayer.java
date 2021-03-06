package nl.elec332.planetside2.ps2api.api.objects.player;

import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2Object;
import nl.elec332.planetside2.ps2api.api.objects.world.IFaction;

/**
 * Created by Elec332 on 04/06/2021
 */
public interface ISlimPlayer extends IPS2Object {

    @Override
    long getId();

    @Override
    String getName();

    IFaction getFaction();

}
