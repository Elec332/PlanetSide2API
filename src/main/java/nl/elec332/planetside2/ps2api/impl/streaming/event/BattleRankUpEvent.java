package nl.elec332.planetside2.ps2api.impl.streaming.event;

import nl.elec332.planetside2.ps2api.api.streaming.event.IBattleRankUpEvent;
import nl.elec332.planetside2.ps2api.impl.streaming.event.base.AbstractPlayerEvent;

import java.io.Serializable;

/**
 * Created by Elec332 on 01/05/2021
 */
public class BattleRankUpEvent extends AbstractPlayerEvent implements IBattleRankUpEvent, Serializable {

    private int battle_rank;

    @Override
    public int getNewBattleRank() {
        return battle_rank;
    }

}
