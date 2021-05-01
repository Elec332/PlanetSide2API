package nl.elec332.planetside2.impl.objects;

import com.google.gson.annotations.SerializedName;
import nl.elec332.planetside2.api.player.IPlayerClass;
import nl.elec332.planetside2.api.player.IPlayerProfile;
import nl.elec332.planetside2.api.registry.IPS2ObjectReference;
import nl.elec332.planetside2.api.world.IFaction;

import java.util.Objects;

/**
 * Created by Elec332 on 26/04/2021
 */
public class PS2Profile implements IPlayerProfile {

    private int profile_id;
    private IPS2ObjectReference<IPlayerClass> profile_type_id;
    private IPS2ObjectReference<IFaction> faction_id;
    @SerializedName("type2.description")
    private String name;

    @Override
    public long getId() {
        return this.profile_id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public IPlayerClass getPlayerClass() {
        return this.profile_type_id.getObject();
    }

    @Override
    public IFaction getFaction() {
        return this.faction_id.getObject();
    }

    //Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2Profile that = (PS2Profile) o;
        return profile_id == that.profile_id && Objects.equals(profile_type_id, that.profile_type_id) && Objects.equals(faction_id, that.faction_id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profile_id, profile_type_id, faction_id, name);
    }

    @Override
    public String toString() {
        return "PS2Profile{" +
                "profile_id=" + profile_id +
                ", profile_type_id=" + profile_type_id +
                ", faction_id=" + faction_id +
                ", name='" + name + '\'' +
                '}';
    }

}
