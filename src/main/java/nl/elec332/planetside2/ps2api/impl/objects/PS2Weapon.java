package nl.elec332.planetside2.ps2api.impl.objects;

import com.google.gson.annotations.SerializedName;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.ps2api.api.objects.weapons.IItem;
import nl.elec332.planetside2.ps2api.api.objects.weapons.IWeapon;

import java.util.Objects;
import java.util.UUID;

/**
 * Created by Elec332 on 01/05/2021
 */
public class PS2Weapon implements IWeapon {

    private int weapon_id;
    @SerializedName("item.item.name.en")
    private String name = UUID.randomUUID().toString(); //Because there are a few lines without name or description...
    @SerializedName("item.item_id")
    private IPS2ObjectReference<IItem> item;
    private int weapon_group_id;
    private float turn_modifier;
    private float move_modifier;
    private int sprint_recovery_ms;
    private int equip_ms;
    private int unequip_ms;
    private int to_iron_sights_ms;
    private int from_iron_sights_ms;

    @Override
    public long getId() {
        return this.weapon_id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public IItem getItem() {
        return this.item.getObject();
    }

    @Override
    public int getWeaponGroupId() {
        return this.weapon_group_id;
    }

    @Override
    public float getTurnModifier() {
        return this.turn_modifier;
    }

    @Override
    public float getMoveModifier() {
        return this.move_modifier;
    }

    @Override
    public int getSprintRecoveryTime() {
        return this.sprint_recovery_ms;
    }

    @Override
    public int getEquipTime() {
        return this.equip_ms;
    }

    @Override
    public int getUnequipTime() {
        return this.unequip_ms;
    }

    @Override
    public int getToIronSightsTime() {
        return this.to_iron_sights_ms;
    }

    @Override
    public int getFromIronSightsTime() {
        return this.from_iron_sights_ms;
    }

    //Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2Weapon ps2Weapon = (PS2Weapon) o;
        return weapon_id == ps2Weapon.weapon_id && weapon_group_id == ps2Weapon.weapon_group_id && Float.compare(ps2Weapon.turn_modifier, turn_modifier) == 0 && Float.compare(ps2Weapon.move_modifier, move_modifier) == 0 && sprint_recovery_ms == ps2Weapon.sprint_recovery_ms && equip_ms == ps2Weapon.equip_ms && unequip_ms == ps2Weapon.unequip_ms && to_iron_sights_ms == ps2Weapon.to_iron_sights_ms && from_iron_sights_ms == ps2Weapon.from_iron_sights_ms && Objects.equals(name, ps2Weapon.name) && Objects.equals(item, ps2Weapon.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weapon_id, name, item, weapon_group_id, turn_modifier, move_modifier, sprint_recovery_ms, equip_ms, unequip_ms, to_iron_sights_ms, from_iron_sights_ms);
    }

    @Override
    public String toString() {
        return "PS2Weapon{" +
                "weapon_id=" + weapon_id +
                ", name='" + name + '\'' +
                ", item=" + item +
                ", weapon_group_id=" + weapon_group_id +
                ", turn_modifier=" + turn_modifier +
                ", move_modifier=" + move_modifier +
                ", sprint_recovery_ms=" + sprint_recovery_ms +
                ", equip_ms=" + equip_ms +
                ", unequip_ms=" + unequip_ms +
                ", to_iron_sights_ms=" + to_iron_sights_ms +
                ", from_iron_sights_ms=" + from_iron_sights_ms +
                '}';
    }

}
