package nl.elec332.planetside2.ps2api.impl.streaming.request;

import nl.elec332.planetside2.ps2api.api.streaming.event.base.IStreamingEvent;
import nl.elec332.planetside2.ps2api.api.streaming.request.IStreamingEventType;

import java.util.function.Predicate;

/**
 * Created by Elec332 on 05/05/2021
 */
public class StreamingEventType<T extends IStreamingEvent> implements IStreamingEventType<T> {

    @SafeVarargs
    public StreamingEventType(IStreamingEventType<T> type, int specified, Predicate<T>... tests) {
        this(type.getEventType(), type.getEventName(), type.getEventName() + "_" + specified, tests);
    }

    @SafeVarargs
    public StreamingEventType(Class<T> type, String eventName, Predicate<T>... tests) {
        this(type, eventName, eventName, tests);
    }

    @SafeVarargs
    private StreamingEventType(Class<T> type, String eventName, String eventNameSpecified, Predicate<T>... tests) {
        this.type = type;
        this.eventName = eventName;
        this.eventNameSpecified = eventNameSpecified;
        Predicate<T> test = a -> true;
        for (Predicate<T> p : tests) {
            test = test.and(p);
        }
        this.tests = test;
    }

    private final Class<T> type;
    private final String eventName;
    private final String eventNameSpecified;
    private final Predicate<T> tests;

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

    @Override
    public boolean isValid(T event) {
        return this.tests.test(event);
    }

}
