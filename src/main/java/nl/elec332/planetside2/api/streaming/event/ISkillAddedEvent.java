package nl.elec332.planetside2.api.streaming.event;

import nl.elec332.planetside2.api.streaming.event.base.IPlayerStreamingEvent;

/**
 * Created by Elec332 on 05/05/2021
 */
public interface ISkillAddedEvent extends IPlayerStreamingEvent {

    int getSkillId();

}
