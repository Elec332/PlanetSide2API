package nl.elec332.planetside2.ps2api.impl.objects;

import com.google.gson.annotations.SerializedName;
import nl.elec332.planetside2.ps2api.api.objects.misc.IMetaGameEvent;

import java.util.Objects;

/**
 * Created by Elec332 on 05/05/2021
 */
public class PS2MetaGameEvent implements IMetaGameEvent {

    private int metagame_event_id;
    @SerializedName("name.en")
    private String name;
    @SerializedName("description.en")
    private String description;
    private int type;
    private float experience_bonus;

    @Override
    public long getId() {
        return this.metagame_event_id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public float getExperienceBonus() {
        return this.experience_bonus;
    }

    //Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2MetaGameEvent that = (PS2MetaGameEvent) o;
        return metagame_event_id == that.metagame_event_id && type == that.type && Float.compare(that.experience_bonus, experience_bonus) == 0 && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metagame_event_id, name, description, type, experience_bonus);
    }

    @Override
    public String toString() {
        return "PS2MetaGameEvent{" +
                "metagame_event_id=" + metagame_event_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", experience_bonus=" + experience_bonus +
                '}';
    }

}
