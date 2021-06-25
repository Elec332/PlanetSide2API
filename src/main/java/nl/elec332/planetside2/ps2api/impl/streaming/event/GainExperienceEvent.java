package nl.elec332.planetside2.ps2api.impl.streaming.event;

import nl.elec332.planetside2.ps2api.api.objects.player.IExperienceType;
import nl.elec332.planetside2.ps2api.api.objects.player.ILoadout;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.ps2api.api.streaming.event.IGainExperienceEvent;
import nl.elec332.planetside2.ps2api.impl.streaming.event.base.AbstractPlayerEvent;

import java.io.Serializable;

/**
 * Created by Elec332 on 02/05/2021
 */
public class GainExperienceEvent extends AbstractPlayerEvent implements IGainExperienceEvent, Serializable {

    private int amount;
    private IPS2ObjectReference<IExperienceType> experience_id;
    private IPS2ObjectReference<ILoadout> loadout_id;
    private long other_id;

    @Override
    public int getExperienceAmount() {
        return this.amount;
    }

    @Override
    public IExperienceType getExperienceType() {
        return this.experience_id.getObject();
    }

    @Override
    public ILoadout getLoadout() {
        return this.loadout_id.getObject();
    }

    @Override
    public long getOtherPlayerId() {
        return this.other_id;
    }

}
