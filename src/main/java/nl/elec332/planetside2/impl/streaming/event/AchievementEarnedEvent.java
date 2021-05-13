package nl.elec332.planetside2.impl.streaming.event;

import nl.elec332.planetside2.api.objects.player.IAchievement;
import nl.elec332.planetside2.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.api.streaming.event.IAchievementEarnedEvent;
import nl.elec332.planetside2.impl.streaming.event.base.AbstractPlayerEvent;

import java.io.Serializable;

/**
 * Created by Elec332 on 01/05/2021
 */
public class AchievementEarnedEvent extends AbstractPlayerEvent implements IAchievementEarnedEvent, Serializable {

    private IPS2ObjectReference<IAchievement> achievement_id;

    @Override
    public IAchievement getAchievement() {
        return this.achievement_id.getObject();
    }

}
