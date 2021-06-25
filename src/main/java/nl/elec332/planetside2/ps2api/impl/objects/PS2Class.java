package nl.elec332.planetside2.ps2api.impl.objects;

import nl.elec332.planetside2.ps2api.api.objects.player.IPlayerClass;

import java.util.Objects;

/**
 * Created by Elec332 on 26/04/2021
 */
public class PS2Class implements IPlayerClass {

    public PS2Class(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private final int id;
    private final String name;

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    //Auto-generated

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2Class ps2Class = (PS2Class) o;
        return id == ps2Class.id && Objects.equals(name, ps2Class.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "PS2Class{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
