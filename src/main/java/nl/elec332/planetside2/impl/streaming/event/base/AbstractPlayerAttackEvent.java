package nl.elec332.planetside2.impl.streaming.event.base;

import nl.elec332.planetside2.api.objects.player.ILoadout;
import nl.elec332.planetside2.api.objects.player.IPlayer;
import nl.elec332.planetside2.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.api.objects.weapons.IVehicle;
import nl.elec332.planetside2.api.objects.weapons.IWeapon;
import nl.elec332.planetside2.api.streaming.event.base.IPlayerAttackStreamingEvent;
import nl.elec332.planetside2.impl.PS2APIAccessor;

/**
 * Created by Elec332 on 01/05/2021
 */
public abstract class AbstractPlayerAttackEvent extends AbstractPlayerEvent implements IPlayerAttackStreamingEvent {

    private long attacker_character_id;
    private IPS2ObjectReference<ILoadout> attacker_loadout_id;
    private IPS2ObjectReference<IVehicle> attacker_vehicle_id;
    private IPS2ObjectReference<IWeapon> attacker_weapon_id;
    private IPS2ObjectReference<IVehicle> vehicle_id;

    @Override
    public long getAttackerId() {
        return this.attacker_character_id;
    }

    @Override
    public IPlayer getAttacker() {
        return PS2APIAccessor.INSTANCE.getAPI().getPlayerManager().get(this.attacker_character_id);
    }

    @Override
    public ILoadout getAttackerLoadout() {
        return this.attacker_loadout_id.getObject();
    }

    @Override
    public IVehicle getAttackerVehicle() {
        return this.attacker_vehicle_id.getObject();
    }

    @Override
    public IWeapon getAttackerWeapon() {
        return this.attacker_weapon_id.getObject();
    }

    @Override
    public IVehicle getVehicle() {
        return vehicle_id.getObject();
    }

}
