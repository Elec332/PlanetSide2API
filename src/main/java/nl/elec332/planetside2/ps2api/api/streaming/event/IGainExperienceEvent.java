package nl.elec332.planetside2.ps2api.api.streaming.event;

import nl.elec332.planetside2.ps2api.api.objects.player.IExperienceType;
import nl.elec332.planetside2.ps2api.api.objects.player.ILoadout;
import nl.elec332.planetside2.ps2api.api.streaming.event.base.IPlayerStreamingEvent;

/**
 * Created by Elec332 on 02/05/2021
 */
public interface IGainExperienceEvent extends IPlayerStreamingEvent {

    int getExperienceAmount();

    IExperienceType getExperienceType();

    ILoadout getLoadout();

    long getOtherPlayerId();

}
