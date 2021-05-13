package nl.elec332.planetside2.impl.streaming.event;

import nl.elec332.planetside2.api.objects.player.ILoadout;
import nl.elec332.planetside2.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.api.streaming.event.IDeathEvent;
import nl.elec332.planetside2.impl.streaming.event.base.AbstractPlayerAttackEvent;

import java.io.Serializable;

/**
 * Created by Elec332 on 01/05/2021
 */
public class DeathEvent extends AbstractPlayerAttackEvent implements IDeathEvent, Serializable {

    private byte is_critical;
    private byte is_headshot;
    private int attacker_fire_mode_id;
    private IPS2ObjectReference<ILoadout> character_loadout_id;

    @Override
    public int getAttackerFireMode() {
        return attacker_fire_mode_id;
    }

    @Override
    public ILoadout getLoadout() {
        return this.character_loadout_id.getObject();
    }

    @Override
    public boolean isCritical() {
        return is_critical != 0;
    }

    @Override
    public boolean isHeadshot() {
        return is_headshot != 0;
    }

}
