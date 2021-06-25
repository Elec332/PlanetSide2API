package nl.elec332.planetside2.ps2api.api.objects.player;

import java.time.Instant;

/**
 * Created by Elec332 on 25/04/2021
 */
public interface IOutfitMember {

    long getPlayerId();

    String getPlayerName();

    IOutfit getOutfit();

    Instant getLastPlayerActivity();

    Instant getJoinDate();

    int getRankIndex();

    default String getRankName() {
        return getOutfit().getRankName(getRankIndex());
    }

}
