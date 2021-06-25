package nl.elec332.planetside2.ps2api.api.objects.player;

import nl.elec332.planetside2.ps2api.api.objects.world.IFaction;

import java.time.Instant;

/**
 * Created by Elec332 on 24/04/2021
 */
public interface IPlayer extends ISlimPlayer {

    @Override
    long getId();

    @Override
    String getName();

    @Override
    IFaction getFaction();

    Instant getCreationDate();

    Instant getLastLoginDate();

    Instant getLastSaveDate();

    int getBattleRank();

    boolean hasASP();

    IOutfit getOutfit();

}
