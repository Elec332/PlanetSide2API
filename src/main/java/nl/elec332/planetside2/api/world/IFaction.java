package nl.elec332.planetside2.api.world;

import nl.elec332.planetside2.api.registry.IPS2Object;

/**
 * Created by Elec332 on 23/04/2021
 */
public interface IFaction extends IPS2Object {

    @Override
    long getId();

    @Override
    String getName();

    String getTag();

    boolean valid();

}
