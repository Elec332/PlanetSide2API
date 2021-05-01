package nl.elec332.planetside2.impl.objects;

import com.google.gson.annotations.SerializedName;
import nl.elec332.planetside2.api.world.IServer;

import java.util.Objects;

/**
 * Created by Elec332 on 23/04/2021
 */
public class PS2Server implements IServer {

    private long world_id;
    @SerializedName("name.en")
    private String name;
    private String state;

    @Override
    public long getId() {
        return this.world_id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isOnline() {
        return this.state.equals("online");
    }

    @Override
    public String status() {
        return this.state;
    }

    //Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2Server ps2Server = (PS2Server) o;
        return world_id == ps2Server.world_id && Objects.equals(state, ps2Server.state) && Objects.equals(name, ps2Server.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(world_id, state, name);
    }

    @Override
    public String toString() {
        return "PS2Server{" +
                "id=" + world_id +
                ", online=" + isOnline() +
                ", name='" + name + '\'' +
                ", status='" + state + '\'' +
                '}';
    }

}
