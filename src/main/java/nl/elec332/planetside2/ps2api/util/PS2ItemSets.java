package nl.elec332.planetside2.ps2api.util;

import nl.elec332.planetside2.ps2api.api.objects.IPS2API;
import nl.elec332.planetside2.ps2api.api.objects.misc.IItemSet;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.ps2api.api.objects.weapons.IItem;
import nl.elec332.planetside2.ps2api.impl.PS2APIAccessor;

/**
 * Created by Elec332 on 30/04/2021
 */
public class PS2ItemSets {

    private static final IPS2API API = PS2APIAccessor.INSTANCE.getAPI();

    public static final IPS2ObjectReference<? extends IItem> BASTION = API.getItems().getReference(API.getItems().getByName("BASTION").getId());

    public static final IItemSet TR_AA_NOSE_GUNS = API.getItemCategories().getByName("Mosquito Nose Cannon");
    public static final IItemSet NC_AA_NOSE_GUNS = API.getItemCategories().getByName("Scythe Nose Cannon");
    public static final IItemSet VS_AA_NOSE_GUNS = API.getItemCategories().getByName("Reaver Nose Cannon");
    public static final IItemSet ALL_AA_NOSE_GUNS = TR_AA_NOSE_GUNS.and(NC_AA_NOSE_GUNS, VS_AA_NOSE_GUNS);
    public static final IItemSet AI_NOSE_GUNS = IItemSet.wrap(4906, 4605, 4305); //Banshee, AirHammer & PPA

}
