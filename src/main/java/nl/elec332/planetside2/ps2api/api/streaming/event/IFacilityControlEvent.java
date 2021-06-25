package nl.elec332.planetside2.ps2api.api.streaming.event;

import nl.elec332.planetside2.ps2api.api.objects.player.IOutfit;
import nl.elec332.planetside2.ps2api.api.objects.world.IFacility;
import nl.elec332.planetside2.ps2api.api.objects.world.IFaction;
import nl.elec332.planetside2.ps2api.api.streaming.event.base.IStreamingEvent;

/**
 * Created by Elec332 on 01/05/2021
 */
public interface IFacilityControlEvent extends IStreamingEvent {

    int getDurationHeld();

    IFacility getFacility();

    IFaction getNewFaction();

    IFaction getOldFaction();

    long getOutfitId();

    IOutfit getOutfit();

}
