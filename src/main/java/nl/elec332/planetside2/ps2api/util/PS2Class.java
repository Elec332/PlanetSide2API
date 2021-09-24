package nl.elec332.planetside2.ps2api.util;

import nl.elec332.planetside2.ps2api.api.objects.player.IPlayerClass;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2ObjectRegistry;

import java.util.Objects;

/**
 * Created by Elec332 on 12/09/2021
 */
public enum PS2Class {

    INFILTRATOR(1, "Infiltrator"),
    LIGHT_ASSAULT(3, "Light Assault"),
    COMBAT_MEDIC(4, "Combat Medic"),
    ENGINEER(5, "Engineer"),
    HEAVY_ASSAULT(6, "Heavy Assault"),
    MAX(7, "MAX")

    ;

    PS2Class(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private final int id;
    private final String name;

    public int getId() {
        return this.id;
    }

    public IPlayerClass getClass(IPS2ObjectRegistry<? extends IPlayerClass> registry) {
        return Objects.requireNonNull(registry.get(getId()));
    }

    @Override
    public String toString() {
        return this.name;
    }

}
