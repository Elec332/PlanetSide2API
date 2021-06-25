package nl.elec332.planetside2.ps2api.impl.streaming.event.base;

import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.ps2api.api.objects.world.IContinent;
import nl.elec332.planetside2.ps2api.api.objects.world.IServer;
import nl.elec332.planetside2.ps2api.api.streaming.event.base.IStreamingEvent;

import java.time.Instant;

/**
 * Created by Elec332 on 01/05/2021
 */
public abstract class AbstractEvent implements IStreamingEvent {

    private String event_name;
    private String event_type;
    private Instant timestamp;
    private IPS2ObjectReference<IServer> world_id;
    private IPS2ObjectReference<IContinent> zone_id;

    @Override
    public String getEventName() {
        return this.event_name == null ? event_type : event_name; //event_type when polling, event_name in streaming API...
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
