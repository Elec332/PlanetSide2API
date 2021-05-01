package nl.elec332.planetside2.impl.objects;

import com.google.gson.annotations.SerializedName;
import nl.elec332.planetside2.api.weapons.ICertificationLine;

import java.util.Objects;
import java.util.UUID;

/**
 * Created by Elec332 on 27/04/2021
 */
public class PS2CertificationLine implements ICertificationLine {

    private int skill_set_id;
    @SerializedName("name.en")
    private String name = UUID.randomUUID().toString(); //Because there are a few lines without name or description...
    @SerializedName("description.en")
    private String description;

    @Override
    public long getId() {
        return this.skill_set_id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    //Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2CertificationLine that = (PS2CertificationLine) o;
        return skill_set_id == that.skill_set_id && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skill_set_id, name, description);
    }

    @Override
    public String toString() {
        return "PS2CertificationLine{" +
                "skill_set_id=" + skill_set_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
