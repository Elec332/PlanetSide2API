package nl.elec332.planetside2.impl.streaming.event.base;

import nl.elec332.planetside2.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.api.objects.world.IContinent;
import nl.elec332.planetside2.api.objects.world.IServer;
import nl.elec332.planetside2.api.streaming.event.base.IStreamingEvent;

import java.time.Instant;

/**
 * Created by Elec332 on 01/05/2021
 */
public abstract class AbstractEvent implements IStreamingEvent {

    private String event_name;
    private Instant timestamp;
    private IPS2ObjectReference<IServer> world_id;
    private IPS2ObjectReference<IContinent> zone_id;

    @Override
    public String getEventName() {
        return this.event_name;
    }

    @Override
    public Instant getTimeStamp() {
        return this.timestamp;
    }

    @Override
    public IServer getServer() {
        return this.world_id.getObject();
    }

    @Override
    public IContinent getContinent() {
        return this.zone_id.getObject();
    }

}