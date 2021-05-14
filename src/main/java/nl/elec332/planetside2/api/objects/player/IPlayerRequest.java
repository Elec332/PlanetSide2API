package nl.elec332.planetside2.api.objects.player;

import nl.elec332.planetside2.api.objects.world.IFaction;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Created by Elec332 on 26/04/2021
 */
public interface IPlayerRequest<R> {

    long getPlayerId();

    String getName();

    IFaction getPlayerFaction();

    Collection<R> getResponse();

    R getFirstResponse();

    Stream<R> getResponseByName(String name);

    R getFirstResponseByName(String name);

}
