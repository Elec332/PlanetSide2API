package nl.elec332.planetside2.ps2api.impl.objects;

import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.ps2api.api.objects.world.IContinent;
import nl.elec332.planetside2.ps2api.api.objects.world.IFacility;
import nl.elec332.planetside2.ps2api.api.objects.world.IFacilityType;
import nl.elec332.planetside2.ps2api.api.objects.world.IMapHex;
import nl.elec332.planetside2.ps2api.impl.objects.misc.PS2MapHex;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Created by Elec332 on 24/04/2021
 */
public class PS2Facility implements IFacility, Serializable {

    private int map_region_id;
    private IPS2ObjectReference<IContinent> zone_id;
    private int facility_id;
    private String facility_name;
    private IPS2ObjectReference<IFacilityType> facility_type_id;
    private float location_x, location_y, location_z;
    private int reward_amount;
    private int reward_currency_id;
    private List<PS2MapHex> hexes;

    @Override
    public long getId() {
        return this.facility_id;
    }

    @Override
    public String getName() {
        return this.facility_name;
    }

    @Override
    public int getMapRegionId() {
        return this.map_region_id;
    }

    @Override
    public IContinent getContinent() {
        return this.zone_id.getObject();
    }

    @Override
    public IFacilityType getFacilityType() {
        return this.facility_type_id.getObject();
    }

    @Override
    public float getX() {
        return this.location_x;
    }

    @Override
    public float getY() {
        return this.location_y;
    }

    @Override
    public float getZ() {
        return this.location_z;
    }

    @Override
    public int getRewardAmount() {
        return this.reward_amount;
    }

    @Override
    public int getCurrencyId() {
        return this.reward_currency_id;
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Collection<IMapHex> getHexes() {
        return (Collection) hexes;
    }

    //Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2Facility ps2Base = (PS2Facility) o;
        return map_region_id == ps2Base.map_region_id && facility_id == ps2Base.facility_id && Float.compare(ps2Base.location_x, location_x) == 0 && Float.compare(ps2Base.location_y, location_y) == 0 && Float.compare(ps2Base.location_z, location_z) == 0 && reward_amount == ps2Base.reward_amount && reward_currency_id == ps2Base.reward_currency_id && Objects.equals(zone_id, ps2Base.zone_id) && Objects.equals(facility_name, ps2Base.facility_name) && Objects.equals(facility_type_id, ps2Base.facility_type_id) && Objects.equals(hexes, ps2Base.hexes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map_region_id, zone_id, facility_id, facility_name, facility_type_id, location_x, location_y, location_z, reward_amount, reward_currency_id, hexes);
    }

    @Override
    public String toString() {
        return "PS2Base{" +
                "map_region_id=" + map_region_id +
                ", zone_id=" + zone_id +
                ", facility_id=" + facility_id +
                ", facility_name='" + facility_name + '\'' +
                ", facility_type_id=" + facility_type_id +
                ", location_x=" + location_x +
                ", location_y=" + location_y +
                ", location_z=" + location_z +
                ", reward_amount=" + reward_amount +
                ", reward_currency_id=" + reward_currency_id +
                ", hexes=" + hexes +
                '}';
    }

}
