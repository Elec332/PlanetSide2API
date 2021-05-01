package nl.elec332.planetside2.api.weapons;

import nl.elec332.planetside2.api.registry.IPS2Object;
import nl.elec332.planetside2.api.world.IFaction;

/**
 * Created by Elec332 on 26/04/2021
 */
public interface IVehicle extends IPS2Object {

    @Override
    long getId();

    @Override
    String getName();

    IFaction getFaction();

}
