package nl.elec332.planetside2.impl.objects;

import nl.elec332.planetside2.api.objects.world.IContinent;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Elec332 on 23/04/2021
 */
public class PS2Continent implements IContinent, Serializable {

    private long zone_id;
    private String code;
    private int hex_size;

    @Override
    public long getId() {
        return this.zone_id;
    }

    @Override
    public String getName() {
        return this.code;
    }

    @Override
    public int getHexSize() {
        return this.hex_size;
    }

    //Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2Continent that = (PS2Continent) o;
        return zone_id == that.zone_id && hex_size == that.hex_size && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zone_id, code, hex_size);
    }

    @Override
    public String toString() {
        return "PS2Continent{" +
                "zone_id=" + zone_id +
                ", code='" + code + '\'' +
                ", hex_size=" + hex_size +
                '}';
    }

}
