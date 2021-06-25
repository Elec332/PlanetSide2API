package nl.elec332.planetside2.ps2api.api.objects.world;

import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2Object;

import java.util.Collection;

/**
 * Created by Elec332 on 24/04/2021
 */
public interface IFacility extends IPS2Object {

    @Override
    long getId();

    @Override
    String getName();

    int getMapRegionId();

    IContinent getContinent();

    IFacilityType getFacilityType();

    float getX();

    float getY();

    float getZ();

    int getRewardAmount();

    int getCurrencyId();

    Collection<IMapHex> getHexes();

}
