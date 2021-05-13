package nl.elec332.planetside2.impl.objects;

import com.google.gson.annotations.SerializedName;
import nl.elec332.planetside2.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.api.objects.weapons.IVehicle;
import nl.elec332.planetside2.api.objects.world.IFaction;

import java.util.Objects;

/**
 * Created by Elec332 on 26/04/2021
 */
public class PS2Vehicle implements IVehicle {

    private int vehicle_id;
    @SerializedName("name.en")
    private String name;
    @SerializedName("faction.faction_id")
    private IPS2ObjectReference<IFaction> faction;

    @Override
    public long getId() {
        return this.vehicle_id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public IFaction getFaction() {
        if (vehicle_id < 100 && faction != null && faction.getId() != 4) {
            return faction.getObject();
        }
        return null;
    }

    //Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2Vehicle that = (PS2Vehicle) o;
        return vehicle_id == that.vehicle_id && Objects.equals(name, that.name) && Objects.equals(faction, that.faction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicle_id, name, faction);
    }

    @Override
    public String toString() {
        return "PS2Vehicle{" +
                "vehicle_id=" + vehicle_id +
                ", name='" + name + '\'' +
                ", faction=" + getFaction() +
                '}';
    }

}
