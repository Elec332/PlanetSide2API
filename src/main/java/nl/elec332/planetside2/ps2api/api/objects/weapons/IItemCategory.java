package nl.elec332.planetside2.ps2api.api.objects.weapons;

import nl.elec332.planetside2.ps2api.api.objects.misc.IItemSet;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2Object;

/**
 * Created by Elec332 on 27/04/2021
 */
public interface IItemCategory extends IPS2Object, IItemSet {

    @Override
    long getId();

    @Override
    String getName();

}
