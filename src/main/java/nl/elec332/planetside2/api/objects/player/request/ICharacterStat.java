package nl.elec332.planetside2.api.objects.player.request;

import nl.elec332.planetside2.api.objects.player.IPlayerProfile;

import java.time.Instant;

/**
 * Created by Elec332 on 27/04/2021
 */
public interface ICharacterStat extends IStat {

    @Override
    String getStatName();

    IPlayerProfile getProfile();

    int getForever();

    int getMonthly();

    int getWeekly();

    int getDaily();

    int getOneLifeMax();

    Instant getLastSave();
}
