package nl.elec332.planetside2.impl.streaming.request;

import nl.elec332.planetside2.api.streaming.event.base.IStreamingEvent;
import nl.elec332.planetside2.api.streaming.request.IStreamingEventType;

/**
 * Created by Elec332 on 05/05/2021
 */
public class StreamingEventType<T extends IStreamingEvent> implements IStreamingEventType<T> {


    public StreamingEventType(IStreamingEventType<T> type, int specified) {
        this(type.getEventType(), type.getEventName(), type.getEventName() + "_" + specified);
    }

    public StreamingEventType(Class<T> type, String eventName) {
        this(type, eventName, eventName);
    }

    private StreamingEventType(Class<T> type, String eventName, String eventNameSpecified) {
        this.type = type;
        this.eventName = eventName;
        this.eventNameSpecified = eventNameSpecified;
    }

    private final Class<T> type;
    private final String eventName;
    private final String eventNameSpecified;

    @Override
    public String getEventName() {
        return this.eventName;
    }

    @Override
    public String getSpecifiedEventName() {
        return this.eventNameSpecified;
    }

    @Override
    public Class<T> getEventType() {
        return this.type;
    }

}
