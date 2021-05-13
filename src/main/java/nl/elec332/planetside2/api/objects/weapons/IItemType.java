package nl.elec332.planetside2.api.objects.weapons;

import nl.elec332.planetside2.api.objects.misc.IItemSet;
import nl.elec332.planetside2.api.objects.registry.IPS2Object;

/**
 * Created by Elec332 on 27/04/2021
 */
public interface IItemType extends IPS2Object, IItemSet {

    @Override
    long getId();

    @Override
    String getName();

    String getFullName();

}
