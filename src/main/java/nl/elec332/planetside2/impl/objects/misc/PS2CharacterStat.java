package nl.elec332.planetside2.impl.objects.misc;

import nl.elec332.planetside2.api.player.IPlayerProfile;
import nl.elec332.planetside2.api.player.request.ICharacterStat;
import nl.elec332.planetside2.api.registry.IPS2ObjectReference;

import java.time.Instant;

/**
 * Created by Elec332 on 27/04/2021
 */
public class PS2CharacterStat implements ICharacterStat {

    private String stat_name;
    private IPS2ObjectReference<IPlayerProfile> profile_id;
    private int value_forever;
    private int value_monthly;
    private int value_weekly;
    private int value_daily;
    private int value_one_life_max;
    private Instant last_save;

    @Override
    public String getStatName() {
        return this.stat_name;
    }

    @Override
    public IPlayerProfile getProfile() {
        return this.profile_id.getObject();
    }

    @Override
    public int getForever() {
        return this.value_forever;
    }

    @Override
    public int getMonthly() {
        return this.value_monthly;
    }

    @Override
    public int getWeekly() {
        return this.value_weekly;
    }

    @Override
    public int getDaily() {
        return this.value_daily;
    }

    @Override
    public int getOneLifeMax() {
        return this.value_one_life_max;
    }

    @Override
    public Instant getLastSave() {
        return this.last_save;
    }

}
