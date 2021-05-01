package nl.elec332.planetside2.impl.objects.misc;

import nl.elec332.planetside2.api.player.request.IFactionWeaponStat;
import nl.elec332.planetside2.api.world.IFaction;

import java.time.Instant;
import java.util.Locale;

/**
 * Created by Elec332 on 26/04/2021
 */
public class PS2FactionWeaponStat implements IFactionWeaponStat {

    private String stat_name;
    private long item_id;
    private int vehicle_id;
    private int value_vs, value_nc, value_tr;
    private Instant last_save;

    @Override
    public String getStatName() {
        return this.stat_name;
    }

    @Override
    public long getItemId() {
        return this.item_id;
    }

    @Override
    public int getVehicleId() {
        return this.vehicle_id;
    }

    @Override
    public int getVSValue() {
        return this.value_vs;
    }

    @Override
    public int getNCValue() {
        return this.value_nc;
    }

    @Override
    public int getTRValue() {
        return this.value_tr;
    }

    @Override
    public int getEnemyKills(IFaction playerFaction) {
        String tag = playerFaction.getTag().toLowerCase(Locale.ROOT);
        if (tag.equals("vs")) {
            return getVSValue();
        }
        if (tag.equals("nc")) {
            return getNCValue();
        }
        if (tag.equals("tr")) {
            return getTRValue();
        }
        return 0;
    }

    @Override
    public Instant getLastSave() {
        return this.last_save;
    }

}
