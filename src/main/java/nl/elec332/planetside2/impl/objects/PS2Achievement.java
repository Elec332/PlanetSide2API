package nl.elec332.planetside2.impl.objects;

import com.google.gson.annotations.SerializedName;
import nl.elec332.planetside2.api.objects.player.IAchievement;
import nl.elec332.planetside2.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.api.objects.weapons.IItem;

import java.util.Objects;

/**
 * Created by Elec332 on 01/05/2021
 */
public class PS2Achievement implements IAchievement {

    private int achievement_id;
    private IPS2ObjectReference<IItem> item_id;
    private byte repeatable;
    @SerializedName("name.en")
    private String name;
    @SerializedName("description.en")
    private String description;

    @Override
    public long getId() {
        return this.achievement_id;
    }

    @Override
    public String getName() {
        return this.name + " " + this.description;
    }

    @Override
    public IItem getItem() {
        return this.item_id.getObject();
    }

    @Override
    public boolean isRepeatable() {
        return this.repeatable != 0;
    }

    //Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2Achievement that = (PS2Achievement) o;
        return achievement_id == that.achievement_id && repeatable == that.repeatable && Objects.equals(item_id, that.item_id) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(achievement_id, item_id, repeatable, name, description);
    }

    @Override
    public String toString() {
        return "PS2Achievement{" +
                "achievement_id=" + achievement_id +
                ", item_id=" + item_id +
                ", repeatable=" + repeatable +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
