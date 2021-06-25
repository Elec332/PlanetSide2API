package nl.elec332.planetside2.ps2api.impl.streaming.event;

import nl.elec332.planetside2.ps2api.api.objects.player.IOutfit;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.ps2api.api.objects.world.IFacility;
import nl.elec332.planetside2.ps2api.api.streaming.event.IPlayerFacilityEvent;
import nl.elec332.planetside2.ps2api.impl.PS2APIAccessor;
import nl.elec332.planetside2.ps2api.impl.streaming.event.base.AbstractPlayerEvent;

import java.io.Serializable;

/**
 * Created by Elec332 on 05/05/2021
 */
public class PlayerFacilityEvent extends AbstractPlayerEvent implements IPlayerFacilityEvent, Serializable {

    private IPS2ObjectReference<IFacility> facility_id;
    private long outfit_id;

    @Override
    public IFacility getFacility() {
        return facility_id.getObject();
    }

    @Override
    public long getOutfitId() {
        return outfit_id;
    }

    @Override
    public IOutfit getOutfit() {
        return PS2APIAccessor.INSTANCE.getAPI().getOutfitManager().getCached(this.outfit_id);
    }
}
