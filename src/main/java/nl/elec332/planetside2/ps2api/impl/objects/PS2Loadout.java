package nl.elec332.planetside2.ps2api.impl.objects;

import nl.elec332.planetside2.ps2api.api.objects.player.ILoadout;
import nl.elec332.planetside2.ps2api.api.objects.player.IPlayerProfile;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2ObjectReference;

import java.util.Objects;

/**
 * Created by Elec332 on 01/05/2021
 */
public class PS2Loadout implements ILoadout {

    private int loadout_id;
    private IPS2ObjectReference<IPlayerProfile> profile_id;
    private String code_name;

    @Override
    public long getId() {
        return this.loadout_id;
    }

    @Override
    public String getName() {
        return this.code_name;
    }

    @Override
    public IPlayerProfile getProfile() {
        return this.profile_id.getObject();
    }

    //Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2Loadout that = (PS2Loadout) o;
        return loadout_id == that.loadout_id && Objects.equals(profile_id, that.profile_id) && Objects.equals(code_name, that.code_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loadout_id, profile_id, code_name);
    }

    @Override
    public String toString() {
        return "PS2Loadout{" +
                "loadout_id=" + loadout_id +
                ", profile_id=" + profile_id +
                ", code_name='" + code_name + '\'' +
                '}';
    }

}
