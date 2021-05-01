package nl.elec332.planetside2.api.player;

import nl.elec332.planetside2.api.registry.IPS2Object;
import nl.elec332.planetside2.api.world.IFaction;

import java.time.Instant;

/**
 * Created by Elec332 on 24/04/2021
 */
public interface IPlayer extends IPS2Object {

    @Override
    long getId();

    @Override
    String getName();

    IFaction getFaction();

    Instant getCreationDate();

    Instant getLastLoginDate();

    Instant getLastSaveDate();

    int getBattleRank();

    boolean hasASP();

    IOutfit getOutfit();

}
