package nl.elec332.planetside2.impl.objects;

import nl.elec332.planetside2.api.objects.misc.IMetaGameEventState;

import java.util.Objects;

/**
 * Created by Elec332 on 05/05/2021
 */
public class PS2MetaGameEventState implements IMetaGameEventState {

    private int metagame_event_state_id;
    private String name;

    @Override
    public long getId() {
        return this.metagame_event_state_id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    //Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2MetaGameEventState that = (PS2MetaGameEventState) o;
        return metagame_event_state_id == that.metagame_event_state_id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metagame_event_state_id, name);
    }

    @Override
    public String toString() {
        return "PS2MetaGameEventState{" +
                "metagame_event_state_id=" + metagame_event_state_id +
                ", name='" + name + '\'' +
                '}';
    }

}
