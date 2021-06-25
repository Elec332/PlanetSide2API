package nl.elec332.planetside2.ps2api.impl.streaming.event;

import nl.elec332.planetside2.ps2api.api.objects.player.IAchievement;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.ps2api.api.streaming.event.IAchievementEarnedEvent;
import nl.elec332.planetside2.ps2api.impl.streaming.event.base.AbstractPlayerEvent;

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
