package nl.elec332.planetside2.ps2api.impl.objects;

import nl.elec332.planetside2.ps2api.api.objects.player.IExperienceType;

import java.util.Objects;

/**
 * Created by Elec332 on 27/04/2021
 */
public class PS2ExperienceType implements IExperienceType {

    private int experience_id;
    private String description;
    private float xp; //DBG made some values a joke, at a grand total of 2.4XP...

    @Override
    public long getId() {
        return this.experience_id;
    }

    @Override
    public String getName() {
        return this.description;
    }

    @Override
    public int getXp() {
        return (int) this.xp;
    }

    //Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2ExperienceType that = (PS2ExperienceType) o;
        return experience_id == that.experience_id && xp == that.xp && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(experience_id, description, xp);
    }

    @Override
    public String toString() {
        return "PS2ExperienceType{" +
                "experience_id=" + experience_id +
                ", description='" + description + '\'' +
                ", xp=" + xp +
                '}';
    }

}
