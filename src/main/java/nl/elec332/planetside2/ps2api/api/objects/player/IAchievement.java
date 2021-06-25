package nl.elec332.planetside2.ps2api.api.objects.player;

import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2Object;
import nl.elec332.planetside2.ps2api.api.objects.weapons.IItem;

/**
 * Created by Elec332 on 01/05/2021
 */
public interface IAchievement extends IPS2Object {

    @Override
    long getId();

    @Override
    String getName();

    IItem getItem();

    boolean isRepeatable();

}
