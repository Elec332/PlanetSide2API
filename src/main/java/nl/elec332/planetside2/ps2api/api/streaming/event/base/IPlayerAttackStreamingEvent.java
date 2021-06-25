package nl.elec332.planetside2.ps2api.api.streaming.event.base;

import nl.elec332.planetside2.ps2api.api.objects.player.ILoadout;
import nl.elec332.planetside2.ps2api.api.objects.player.IPlayer;
import nl.elec332.planetside2.ps2api.api.objects.weapons.IVehicle;
import nl.elec332.planetside2.ps2api.api.objects.weapons.IWeapon;

/**
 * Created by Elec332 on 01/05/2021
 */
public interface IPlayerAttackStreamingEvent extends IPlayerStreamingEvent {

    long getAttackerId();

    IPlayer getAttacker();

    ILoadout getAttackerLoadout();

    IVehicle getAttackerVehicle();

    IWeapon getAttackerWeapon();

    IVehicle getVehicle();

}
