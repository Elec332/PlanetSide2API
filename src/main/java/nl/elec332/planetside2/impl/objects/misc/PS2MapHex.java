package nl.elec332.planetside2.impl.objects.misc;

import nl.elec332.planetside2.api.registry.IPS2ObjectReference;
import nl.elec332.planetside2.api.world.IHexType;
import nl.elec332.planetside2.api.world.IMapHex;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Elec332 on 24/04/2021
 */
public class PS2MapHex implements IMapHex, Serializable {

    private int x, y;
    private IPS2ObjectReference<IHexType> hex_type;

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public IHexType getHexType() {
        return this.hex_type.getObject();
    }

    //Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2MapHex ps2MapHex = (PS2MapHex) o;
        return x == ps2MapHex.x && y == ps2MapHex.y && Objects.equals(hex_type, ps2MapHex.hex_type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, hex_type);
    }

    @Override
    public String toString() {
        return "PS2MapHex{" +
                "x=" + x +
                ", y=" + y +
                ", hex_type=" + hex_type +
                '}';
    }

}
