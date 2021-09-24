package nl.elec332.planetside2.ps2api.api.objects.weapons;

import nl.elec332.planetside2.ps2api.api.objects.IHasImage;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2Object;
import nl.elec332.planetside2.ps2api.api.objects.world.IFaction;

/**
 * Created by Elec332 on 27/04/2021
 */
public interface IItem extends IPS2Object, IHasImage {

    @Override
    long getId();

    @Override
    String getName();

    IItemType getItemType();

    IItemCategory getItemCategory();

    boolean isVehicleWeapon();

    String getDescription();

    IFaction getFaction();

    int getMaxStackSize();

    ICertificationLine getCertificationLine();

    boolean isDefaultAttachment();

}
