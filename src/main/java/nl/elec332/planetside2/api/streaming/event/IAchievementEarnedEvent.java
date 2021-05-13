package nl.elec332.planetside2.api.streaming.event;

import nl.elec332.planetside2.api.objects.player.IAchievement;
import nl.elec332.planetside2.api.streaming.event.base.IPlayerStreamingEvent;

/**
 * Created by Elec332 on 01/05/2021
 */
public interface IAchievementEarnedEvent extends IPlayerStreamingEvent {

    IAchievement getAchievement();

}
