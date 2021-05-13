package nl.elec332.planetside2.api.streaming.event;

import nl.elec332.planetside2.api.objects.world.IFacility;
import nl.elec332.planetside2.api.objects.world.IFaction;
import nl.elec332.planetside2.api.streaming.event.base.IPlayerAttackStreamingEvent;

/**
 * Created by Elec332 on 05/05/2021
 */
public interface IVehicleDestroyEvent extends IPlayerAttackStreamingEvent {

    IFacility getFacility();

    IFaction getFaction();

}
