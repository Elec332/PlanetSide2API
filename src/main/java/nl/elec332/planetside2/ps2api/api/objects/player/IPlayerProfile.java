package nl.elec332.planetside2.ps2api.api.objects.player;

import nl.elec332.planetside2.ps2api.api.objects.IHasImage;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2Object;
import nl.elec332.planetside2.ps2api.api.objects.world.IFaction;

/**
 * Created by Elec332 on 26/04/2021
 */
public interface IPlayerProfile extends IPS2Object, IHasImage {

    @Override
    long getId();

    @Override
    String getName();

    IPlayerClass getPlayerClass();

    IFaction getFaction();

}
