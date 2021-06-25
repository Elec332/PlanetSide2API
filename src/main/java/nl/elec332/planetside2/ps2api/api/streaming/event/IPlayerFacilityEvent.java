package nl.elec332.planetside2.ps2api.api.streaming.event;

import nl.elec332.planetside2.ps2api.api.objects.player.IOutfit;
import nl.elec332.planetside2.ps2api.api.objects.world.IFacility;
import nl.elec332.planetside2.ps2api.api.streaming.event.base.IPlayerStreamingEvent;

/**
 * Created by Elec332 on 05/05/2021
 */
public interface IPlayerFacilityEvent extends IPlayerStreamingEvent {

    IFacility getFacility();

    long getOutfitId();

    IOutfit getOutfit();

}
