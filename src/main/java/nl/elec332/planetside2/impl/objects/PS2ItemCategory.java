package nl.elec332.planetside2.impl.objects;

import com.google.gson.annotations.SerializedName;
import nl.elec332.planetside2.api.weapons.IItemCategory;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;


/**
 * Created by Elec332 on 27/04/2021
 */
public class PS2ItemCategory implements IItemCategory {

    private int item_category_id;
    @SerializedName("name.en")
    private String name;
    private Set<Integer> items_list;

    @Override
    public long getId() {
        return this.item_category_id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Set<Integer> getItems() {
        return Collections.unmodifiableSet(items_list);
    }

    //Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2ItemCategory that = (PS2ItemCategory) o;
        return item_category_id == that.item_category_id && Objects.equals(name, that.name) && Objects.equals(items_list, that.items_list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item_category_id, name, items_list);
    }

    @Override
    public String toString() {
        return "PS2ItemCategory{" +
                "item_category_id=" + item_category_id +
                ", name='" + name + '\'' +
                ", items=" + items_list +
                '}';
    }

}
