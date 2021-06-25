package nl.elec332.planetside2.ps2api.api.streaming.request;

import nl.elec332.planetside2.ps2api.api.streaming.event.base.IStreamingEvent;

/**
 * Created by Elec332 on 01/05/2021
 */
public interface IStreamingEventType<T extends IStreamingEvent> {

    String getEventName();

    String getSpecifiedEventName();

    Class<T> getEventType();

}
