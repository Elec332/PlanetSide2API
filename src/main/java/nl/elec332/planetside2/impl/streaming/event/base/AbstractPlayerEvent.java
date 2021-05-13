package nl.elec332.planetside2.impl.streaming.event.base;

import nl.elec332.planetside2.api.objects.player.IPlayer;
import nl.elec332.planetside2.api.streaming.event.base.IPlayerStreamingEvent;
import nl.elec332.planetside2.impl.PS2APIAccessor;

/**
 * Created by Elec332 on 01/05/2021
 */
public class AbstractPlayerEvent extends AbstractEvent implements IPlayerStreamingEvent {

    private long character_id;

    @Override
    public long getPlayerId() {
        return this.character_id;
    }

    @Override
    public IPlayer getPlayer() {
        return PS2APIAccessor.INSTANCE.getAPI().getPlayerManager().get(this.character_id);
    }

}
