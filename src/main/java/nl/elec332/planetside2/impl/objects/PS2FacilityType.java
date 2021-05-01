package nl.elec332.planetside2.impl.objects;

import nl.elec332.planetside2.api.world.IFacilityType;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Elec332 on 23/04/2021
 */
public class PS2FacilityType implements IFacilityType, Serializable {

    private int facility_type_id;
    private String description;

    @Override
    public long getId() {
        return this.facility_type_id;
    }

    @Override
    public String getName() {
        return this.description;
    }

    //Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2FacilityType that = (PS2FacilityType) o;
        return facility_type_id == that.facility_type_id && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facility_type_id, description);
    }

    @Override
    public String toString() {
        return "PS2FacilityType{" +
                "facility_type_id=" + facility_type_id +
                ", description='" + description + '\'' +
                '}';
    }

}
