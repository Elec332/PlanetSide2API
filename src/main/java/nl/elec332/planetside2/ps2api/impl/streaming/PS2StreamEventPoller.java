package nl.elec332.planetside2.ps2api.impl.streaming;

import nl.elec332.planetside2.ps2api.api.ICensusAPI;
import nl.elec332.planetside2.ps2api.api.streaming.IStreamEventPoller;
import nl.elec332.planetside2.ps2api.api.streaming.event.IFacilityControlEvent;
import nl.elec332.planetside2.ps2api.api.streaming.event.IMetaGameEventEvent;
import nl.elec332.planetside2.ps2api.api.streaming.event.IPlayerFacilityEvent;
import nl.elec332.planetside2.ps2api.api.streaming.event.base.IStreamingEvent;
import nl.elec332.planetside2.ps2api.api.streaming.request.IStreamingEventType;
import nl.elec332.planetside2.ps2api.impl.streaming.request.EventServiceFactory;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by Elec332 on 05/05/2021
 */
public class PS2StreamEventPoller implements IStreamEventPoller {

    public PS2StreamEventPoller(ICensusAPI api) {
        this.api = api;
    }

    private final ICensusAPI api;

    @Override
    public Collection<IPlayerFacilityEvent> getPlayerEvents(IFacilityControlEvent event) {
        long time = event.getTimeStamp().getEpochSecond();
        return api.invokeAPI("event", "type=FACILITY_CHARACTER&c:limit=10000&after=" + (time - 1) + "&before=" + (time + 1), IStreamingEvent.class)
                .map(e -> (IPlayerFacilityEvent) e)
                .filter(e -> e.getContinent() != null && e.getContinent().getId() == event.getContinent().getId())
                .filter(e -> e.getFacility() != null && e.getFacility().getId() == event.getFacility().getId())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<IMetaGameEventEvent> getMetaGameEvents(Instant from, long... servers) {
        return getEvents("world_event", EventServiceFactory.INSTANCE.getMetaGameEventType(), from, servers);
    }

    @Override
    public Collection<IFacilityControlEvent> getFacilityControlEvents(Instant from, long... servers) {
        return getEvents("world_event", EventServiceFactory.INSTANCE.getFacilityControlType(), from, servers);
    }

    @SuppressWarnings("unchecked")
    private <T extends IStreamingEvent> Collection<T> getEvents(String apiName, IStreamingEventType<T> type, Instant from, long[] servers) {
        return api.invokeAPI(apiName, specify("type=" + type.getEventName() + "&c:limit=10000", from, servers), IStreamingEvent.class)
                .map(t -> (T) t)
                .collect(Collectors.toList());
    }

    private String specify(String before, Instant from, long[] servers) {
        if (before != null && !before.isEmpty()) {
            before += "&";
        }
        if (before == null) {
            before = "";
        }
        String req = "";
        if (from != null) {
            req = "after=" + from.getEpochSecond();
        }
        if (servers.length > 0) {
            if (!req.isEmpty()) {
                req += "&";
            }
            req += "world_id=" + Arrays.stream(servers).mapToObj(l -> l + "").collect(Collectors.joining(","));
        }
        return before + req;
    }

}
