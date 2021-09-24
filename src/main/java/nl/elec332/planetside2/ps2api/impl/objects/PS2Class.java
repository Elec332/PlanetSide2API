package nl.elec332.planetside2.ps2api.impl.objects;

import nl.elec332.planetside2.ps2api.api.objects.IHasImage;
import nl.elec332.planetside2.ps2api.api.objects.IPS2API;
import nl.elec332.planetside2.ps2api.api.objects.player.IPlayerClass;
import nl.elec332.planetside2.ps2api.api.objects.world.IFaction;
import nl.elec332.planetside2.ps2api.util.NetworkUtil;

import java.util.Objects;

/**
 * Created by Elec332 on 26/04/2021
 */
public class PS2Class implements IPlayerClass {

    public PS2Class(nl.elec332.planetside2.ps2api.util.PS2Class clazz) {
        this.id = clazz.getId();
        this.name = clazz.toString();
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

    @Override
    public String getMonolithicName() {
        return this.name.replace(" ", "");
    }

    @Override
    public int getImageId() {
        IPS2API api = NetworkUtil.getAPIAccessor().getAPI();
        IFaction faction = api.getFactions().getByName("New Conglomerate");
        return api.getPlayerProfiles().stream()
                .filter(p -> p.getPlayerClass().getId() == getId())
                .filter(p -> p.getFaction().equals(faction))
                .findFirst()
                .map(IHasImage::getImageId)
                .orElse(-1);
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
