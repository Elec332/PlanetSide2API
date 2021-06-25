package nl.elec332.planetside2.ps2api.impl.streaming.event;

import nl.elec332.planetside2.ps2api.api.objects.player.IOutfit;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.ps2api.api.objects.world.IFacility;
import nl.elec332.planetside2.ps2api.api.objects.world.IFaction;
import nl.elec332.planetside2.ps2api.api.streaming.event.IFacilityControlEvent;
import nl.elec332.planetside2.ps2api.impl.PS2APIAccessor;
import nl.elec332.planetside2.ps2api.impl.streaming.event.base.AbstractEvent;

import java.io.Serializable;

/**
 * Created by Elec332 on 01/05/2021
 */
public class FacilityControlEvent extends AbstractEvent implements IFacilityControlEvent, Serializable {

    private int duration_held;
    private IPS2ObjectReference<IFacility> facility_id;
    private IPS2ObjectReference<IFaction> new_faction_id;
    private IPS2ObjectReference<IFaction> old_faction_id;
    private long outfit_id;

    @Override
    public int getDurationHeld() {
        return this.duration_held;
    }

    @Override
    public IFacility getFacility() {
        return this.facility_id.getObject();
    }

    @Override
    public IFaction getNewFaction() {
        return this.new_faction_id.getObject();
    }

    @Override
    public IFaction getOldFaction() {
        return this.old_faction_id.getObject();
    }

    @Override
    public long getOutfitId() {
        return this.outfit_id;
    }

    @Override
    public IOutfit getOutfit() {
        return PS2APIAccessor.INSTANCE.getAPI().getOutfitManager().getCached(this.outfit_id);
    }

    @Override
    public String toString() {
        return "FacilityControlEvent{" + getServer() + "  " +
                "duration_held=" + duration_held +
                ", facility_id=" + facility_id +
                ", new_faction_id=" + new_faction_id +
                ", old_faction_id=" + old_faction_id +
                ", outfit_id=" + outfit_id +
                '}';
    }
}
