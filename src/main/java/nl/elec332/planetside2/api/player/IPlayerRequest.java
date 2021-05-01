package nl.elec332.planetside2.api.player;

import nl.elec332.planetside2.api.world.IFaction;

import java.util.Collection;

/**
 * Created by Elec332 on 26/04/2021
 */
public interface IPlayerRequest<R> {

    long getPlayerId();

    String getName();

    IFaction getPlayerFaction();

    Collection<R> getResponse();

    R getFirstResponse();

    R getResponseByName(String name);

}
