package nl.elec332.planetside2.ps2api.api.objects.world;

import nl.elec332.planetside2.ps2api.api.objects.IHasImage;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2Object;

/**
 * Created by Elec332 on 23/04/2021
 */
public interface IFaction extends IPS2Object, IHasImage {

    @Override
    long getId();

    @Override
    String getName();

    String getTag();

    boolean valid();

}
