package nl.elec332.planetside2.ps2api.impl.objects;

import com.google.gson.annotations.SerializedName;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.ps2api.api.objects.weapons.ICertificationLine;
import nl.elec332.planetside2.ps2api.api.objects.weapons.IItem;
import nl.elec332.planetside2.ps2api.api.objects.weapons.IItemCategory;
import nl.elec332.planetside2.ps2api.api.objects.weapons.IItemType;
import nl.elec332.planetside2.ps2api.api.objects.world.IFaction;

import java.util.Objects;
import java.util.UUID;

/**
 * Created by Elec332 on 27/04/2021
 */
public class PS2Item implements IItem {

    private int item_id;
    private IPS2ObjectReference<IItemType> item_type_id;
    private IPS2ObjectReference<IItemCategory> item_category_id;
    private int is_vehicle_weapon;
    @SerializedName("name.en")
    private String name = UUID.randomUUID().toString(); //Because there are a few lines without name or description...
    @SerializedName("description.en")
    private String description;
    private IPS2ObjectReference<IFaction> faction_id;
    private int max_stack_size;
    private IPS2ObjectReference<ICertificationLine> skill_set_id;
    private int is_default_attachment;

    @Override
    public long getId() {
        return this.item_id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public IItemType getItemType() {
        return this.item_type_id.getObject();
    }

    @Override
    public IItemCategory getItemCategory() {
        return this.item_category_id.getObject();
    }

    @Override
    public boolean isVehicleWeapon() {
        return this.is_vehicle_weapon != 0;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public IFaction getFaction() {
        return this.faction_id.getObject();
    }

    @Override
    public int getMaxStackSize() {
        return this.max_stack_size;
    }

    @Override
    public ICertificationLine getCertificationLine() {
        return this.skill_set_id.getObject();
    }

    @Override
    public boolean isDefaultAttachment() {
        return this.is_default_attachment == 1;
    }

    //Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2Item ps2Item = (PS2Item) o;
        return item_id == ps2Item.item_id && is_vehicle_weapon == ps2Item.is_vehicle_weapon && max_stack_size == ps2Item.max_stack_size && is_default_attachment == ps2Item.is_default_attachment && Objects.equals(item_type_id, ps2Item.item_type_id) && Objects.equals(item_category_id, ps2Item.item_category_id) && Objects.equals(name, ps2Item.name) && Objects.equals(description, ps2Item.description) && Objects.equals(faction_id, ps2Item.faction_id) && Objects.equals(skill_set_id, ps2Item.skill_set_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item_id, item_type_id, item_category_id, is_vehicle_weapon, name, description, faction_id, max_stack_size, skill_set_id, is_default_attachment);
    }

    @Override
    public String toString() {
        return "PS2Item{" +
                "item_id=" + item_id +
                ", item_type_id=" + item_type_id +
                ", item_category_id=" + item_category_id +
                ", is_vehicle_weapon=" + is_vehicle_weapon +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", faction_id=" + faction_id +
                ", max_stack_size=" + max_stack_size +
                ", skill_set_id=" + skill_set_id +
                ", is_default_attachment=" + is_default_attachment +
                '}';
    }

}
