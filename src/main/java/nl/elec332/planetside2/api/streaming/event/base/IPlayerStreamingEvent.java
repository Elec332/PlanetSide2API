package nl.elec332.planetside2.api.streaming.event.base;

import nl.elec332.planetside2.api.objects.player.IPlayer;

/**
 * Created by Elec332 on 01/05/2021
 */
public interface IPlayerStreamingEvent extends IStreamingEvent {

    long getPlayerId();

    IPlayer getPlayer();

}
