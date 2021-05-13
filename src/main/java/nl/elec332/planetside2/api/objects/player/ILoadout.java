package nl.elec332.planetside2.api.objects.player;

import nl.elec332.planetside2.api.objects.registry.IPS2Object;

/**
 * Created by Elec332 on 01/05/2021
 * <p>
 * This object type is stupid
 */
public interface ILoadout extends IPS2Object {

    @Override
    long getId();

    @Override
    String getName();

    IPlayerProfile getProfile();

}
