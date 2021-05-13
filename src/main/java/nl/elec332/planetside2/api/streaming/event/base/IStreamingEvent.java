package nl.elec332.planetside2.api.streaming.event.base;

import nl.elec332.planetside2.api.objects.world.IContinent;
import nl.elec332.planetside2.api.objects.world.IServer;

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
