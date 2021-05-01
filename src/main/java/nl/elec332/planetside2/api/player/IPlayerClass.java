package nl.elec332.planetside2.api.player;

import nl.elec332.planetside2.api.registry.IPS2Object;

/**
 * Created by Elec332 on 26/04/2021
 */
public interface IPlayerClass extends IPS2Object {

    @Override
    long getId();

    @Override
    String getName();

}
