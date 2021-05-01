package nl.elec332.planetside2.api.player.request;

import nl.elec332.planetside2.api.world.IFaction;

import java.time.Instant;

/**
 * Created by Elec332 on 26/04/2021
 */
public interface IFactionWeaponStat extends IStat {

    @Override
    String getStatName();

    long getItemId();

    int getVehicleId();

    int getVSValue();

    int getNCValue();

    int getTRValue();

    default int getTotal() {
        return getNCValue() + getTRValue() + getVSValue();
    }

    int getEnemyKills(IFaction playerFaction);

    Instant getLastSave();

}
