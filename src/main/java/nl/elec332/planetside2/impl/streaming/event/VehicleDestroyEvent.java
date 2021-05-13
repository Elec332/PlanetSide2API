package nl.elec332.planetside2.impl.streaming.event;

import nl.elec332.planetside2.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.api.objects.world.IFacility;
import nl.elec332.planetside2.api.objects.world.IFaction;
import nl.elec332.planetside2.api.streaming.event.IVehicleDestroyEvent;
import nl.elec332.planetside2.impl.streaming.event.base.AbstractPlayerAttackEvent;

import java.io.Serializable;

/**
 * Created by Elec332 on 05/05/2021
 */
public class VehicleDestroyEvent extends AbstractPlayerAttackEvent implements IVehicleDestroyEvent, Serializable {

    private IPS2ObjectReference<IFacility> facility_id;
    private IPS2ObjectReference<IFaction> faction_id;

    @Override
    public IFacility getFacility() {
        return this.facility_id.getObject();
    }

    @Override
    public IFaction getFaction() {
        return this.faction_id.getObject();
    }

}
