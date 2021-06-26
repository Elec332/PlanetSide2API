package nl.elec332.planetside2.ps2api.impl.streaming.event;

import nl.elec332.planetside2.ps2api.api.objects.misc.IMetaGameEvent;
import nl.elec332.planetside2.ps2api.api.objects.misc.IMetaGameEventState;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.ps2api.api.streaming.event.IMetaGameEventEvent;
import nl.elec332.planetside2.ps2api.impl.streaming.event.base.AbstractEvent;

import java.io.Serializable;

/**
 * Created by Elec332 on 05/05/2021
 */
public class MetaGameEventEvent extends AbstractEvent implements IMetaGameEventEvent, Serializable {

    private float experience_bonus;
    private float faction_nc, faction_tr, faction_vs;
    private int instance_id;
    private IPS2ObjectReference<IMetaGameEvent> metagame_event_id;
    private IPS2ObjectReference<IMetaGameEventState> metagame_event_state;

    @Override
    public float getExperienceBonus() {
        return this.experience_bonus;
    }

    @Override
    public float getNCTerritory() {
        return this.faction_nc;
    }

    @Override
    public float getTRTerritory() {
        return this.faction_tr;
    }

    @Override
    public float getVSTerritory() {
        return this.faction_vs;
    }

    @Override
    public int getInstanceId() {
        return this.instance_id;
    }

    @Override
    public IMetaGameEvent getEvent() {
        return this.metagame_event_id.getObject();
    }

    @Override
    public IMetaGameEventState getEventState() {
        return this.metagame_event_state.getObject();
    }

    @Override
    public String toString() {
        return "MetaGameEventEvent{" +
                "experience_bonus=" + experience_bonus +
                ", faction_nc=" + faction_nc +
                ", faction_tr=" + faction_tr +
                ", faction_vs=" + faction_vs +
                ", instance_id=" + instance_id +
                ", metagame_event_id=" + metagame_event_id +
                ", metagame_event_state=" + metagame_event_state +
                ", continent=" + getContinent() +
                '}';
    }
}
