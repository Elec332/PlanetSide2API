package nl.elec332.planetside2.ps2api.impl.objects;

import com.google.gson.annotations.SerializedName;
import nl.elec332.planetside2.ps2api.api.objects.world.IFaction;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Elec332 on 23/04/2021
 */
public class PS2Faction implements IFaction, Serializable {

    private long faction_id;
    @SerializedName("name.en")
    private String name;
    private String code_tag;
    private int user_selectable;

    @Override
    public long getId() {
        return this.faction_id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getTag() {
        return this.code_tag;
    }

    @Override
    public boolean valid() {
        return this.user_selectable == 1;
    }

    //Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2Faction that = (PS2Faction) o;
        return faction_id == that.faction_id && user_selectable == that.user_selectable && Objects.equals(name, that.name) && Objects.equals(code_tag, that.code_tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(faction_id, name, code_tag, user_selectable);
    }

    @Override
    public String toString() {
        return "PS2Faction{" +
                "faction_id=" + faction_id +
                ", name='" + name + '\'' +
                ", code_tag='" + code_tag + '\'' +
                ", user_selectable=" + user_selectable +
                '}';
    }

}
