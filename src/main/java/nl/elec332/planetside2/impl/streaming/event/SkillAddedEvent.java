package nl.elec332.planetside2.impl.streaming.event;

import nl.elec332.planetside2.api.streaming.event.ISkillAddedEvent;
import nl.elec332.planetside2.impl.streaming.event.base.AbstractPlayerEvent;

import java.io.Serializable;

/**
 * Created by Elec332 on 05/05/2021
 */
public class SkillAddedEvent extends AbstractPlayerEvent implements ISkillAddedEvent, Serializable {

    private int skill_id;

    @Override
    public int getSkillId() {
        return this.skill_id;
    }

}
