package nl.elec332.planetside2.impl.objects;

import nl.elec332.planetside2.api.world.IHexType;

import java.util.Objects;

/**
 * Created by Elec332 on 24/04/2021
 */
public class PS2HexType implements IHexType {

    public PS2HexType(int id, String description, boolean factionRestricted) {
        this.id = id;
        this.description = description;
        this.factionRestricted = factionRestricted;
    }

    private final long id;
    private final String description;
    private final boolean factionRestricted;

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.description;
    }

    @Override
    public boolean isFactionRestricted() {
        return this.factionRestricted;
    }

    //Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2HexType that = (PS2HexType) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, factionRestricted);
    }

    @Override
    public String toString() {
        return "PS2HexType{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", factionRestricted=" + factionRestricted +
                '}';
    }

}
