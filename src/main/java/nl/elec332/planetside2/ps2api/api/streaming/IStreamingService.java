package nl.elec332.planetside2.ps2api.api.streaming;

import nl.elec332.planetside2.ps2api.api.objects.world.IServer;
import nl.elec332.planetside2.ps2api.api.streaming.event.base.IStreamingEvent;
import nl.elec332.planetside2.ps2api.api.streaming.request.IStreamingEventType;

import java.util.function.Consumer;

/**
 * Created by Elec332 on 01/05/2021
 */
public interface IStreamingService {

    void clearSubscribe(IStreamingEventType<?>... type);

    void subscribeToEvent(IStreamingEventType<?>... type);

    void subscribeToEvent(IStreamingEventType<?> type, IServer... servers);

    void subscribeToEvent(IStreamingEventType<?> type, long... players);

    <T extends IStreamingEvent> void addListener(IStreamingEventType<T> type, Consumer<T> listener);

    <T extends IStreamingEvent> void removeListener(IStreamingEventType<T> type, Consumer<T> listener);

    void removeListeners(IStreamingEventType<?> type);

    void addHeartBeatListener(Consumer<IHeartBeatMessage> listener);

    void clearSubscriptions();

    void stop();

}
