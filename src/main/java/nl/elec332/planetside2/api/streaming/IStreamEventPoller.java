package nl.elec332.planetside2.api.streaming;

import nl.elec332.planetside2.api.objects.world.IServer;
import nl.elec332.planetside2.api.streaming.event.IFacilityControlEvent;
import nl.elec332.planetside2.api.streaming.event.IMetaGameEventEvent;
import nl.elec332.planetside2.api.streaming.event.IPlayerFacilityEvent;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Elec332 on 05/05/2021
 */
public interface IStreamEventPoller {

    Collection<IPlayerFacilityEvent> getPlayerEvents(IFacilityControlEvent event);

    default Collection<IMetaGameEventEvent> getMetaGameEvents(Instant from, IServer... servers) {
        return getMetaGameEvents(from, Arrays.stream(servers).mapToLong(IServer::getId).toArray());
    }

    Collection<IMetaGameEventEvent> getMetaGameEvents(Instant from, long... servers);

    default Collection<IFacilityControlEvent> getFacilityControlEvents(Instant from, IServer... servers) {
        return getFacilityControlEvents(from, Arrays.stream(servers).mapToLong(IServer::getId).toArray());
    }

    Collection<IFacilityControlEvent> getFacilityControlEvents(Instant from, long... servers);

}
