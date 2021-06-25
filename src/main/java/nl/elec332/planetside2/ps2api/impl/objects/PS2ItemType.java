package nl.elec332.planetside2.ps2api.impl.objects;

import nl.elec332.planetside2.ps2api.api.objects.weapons.IItemType;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Elec332 on 27/04/2021
 */
public class PS2ItemType implements IItemType {

    private int item_type_id;
    private String code;
    private String name;
    private Set<Integer> items_list;

    @Override
    public long getId() {
        return this.item_type_id;
    }

    @Override
    public String getName() {
        return this.code;
    }

    @Override
    public String getFullName() {
        return this.name;
    }

    @Override
    public Set<Integer> getItems() {
        return Collections.unmodifiableSet(this.items_list);
    }

    //Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2ItemType that = (PS2ItemType) o;
        return item_type_id == that.item_type_id && Objects.equals(code, that.code) && Objects.equals(name, that.name) && Objects.equals(items_list, that.items_list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item_type_id, code, name, items_list);
    }

    @Override
    public String toString() {
        return "PS2ItemType{" +
                "item_type_id=" + item_type_id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", items_list=" + items_list +
                '}';
    }

}
