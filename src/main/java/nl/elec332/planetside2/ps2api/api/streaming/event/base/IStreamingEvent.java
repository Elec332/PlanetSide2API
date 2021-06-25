package nl.elec332.planetside2.ps2api.api.streaming.event.base;

import nl.elec332.planetside2.ps2api.api.objects.world.IContinent;
import nl.elec332.planetside2.ps2api.api.objects.world.IServer;

import java.time.Instant;

/**
 * Created by Elec332 on 01/05/2021
 */
public interface IStreamingEvent {

    String getEventName();

    Instant getTimeStamp();

    IServer getServer();

    IContinent getContinent();

}
