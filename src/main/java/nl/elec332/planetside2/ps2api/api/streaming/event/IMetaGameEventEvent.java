package nl.elec332.planetside2.ps2api.api.streaming.event;

import nl.elec332.planetside2.ps2api.api.objects.misc.IMetaGameEvent;
import nl.elec332.planetside2.ps2api.api.objects.misc.IMetaGameEventState;
import nl.elec332.planetside2.ps2api.api.streaming.event.base.IStreamingEvent;

/**
 * Created by Elec332 on 05/05/2021
 */
public interface IMetaGameEventEvent extends IStreamingEvent {

    float getExperienceBonus();

    float getNCTerritory();

    float getTRTerritory();

    float getVSTerritory();

    int getInstanceId();

    IMetaGameEvent getEvent();

    IMetaGameEventState getEventState();

}
