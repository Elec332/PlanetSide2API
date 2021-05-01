package nl.elec332.planetside2.api.weapons;

import nl.elec332.planetside2.api.registry.IPS2Object;

/**
 * Created by Elec332 on 27/04/2021
 */
public interface ICertificationLine extends IPS2Object {

    @Override
    long getId();

    @Override
    String getName();

    String getDescription();

}
