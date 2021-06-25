package nl.elec332.planetside2.ps2api.api.objects.weapons;

import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2Object;

/**
 * Created by Elec332 on 01/05/2021
 */
public interface IWeapon extends IPS2Object {

    @Override
    long getId();

    @Override
    String getName();

    IItem getItem();

    int getWeaponGroupId();

    float getTurnModifier();

    float getMoveModifier();

    //All times below are in milliseconds

    int getSprintRecoveryTime();

    int getEquipTime();

    int getUnequipTime();

    int getToIronSightsTime();

    int getFromIronSightsTime();

}
