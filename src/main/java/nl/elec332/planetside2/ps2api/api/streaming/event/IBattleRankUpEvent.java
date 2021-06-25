package nl.elec332.planetside2.ps2api.api.streaming.event;

import nl.elec332.planetside2.ps2api.api.streaming.event.base.IPlayerStreamingEvent;

/**
 * Created by Elec332 on 01/05/2021
 */
public interface IBattleRankUpEvent extends IPlayerStreamingEvent {

    int getNewBattleRank();

}
