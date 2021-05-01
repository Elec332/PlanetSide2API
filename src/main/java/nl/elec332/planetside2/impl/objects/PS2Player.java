package nl.elec332.planetside2.impl.objects;

import com.google.gson.annotations.SerializedName;
import nl.elec332.planetside2.api.player.IOutfit;
import nl.elec332.planetside2.api.player.IPlayer;
import nl.elec332.planetside2.api.registry.IPS2ObjectReference;
import nl.elec332.planetside2.api.world.IFaction;

import java.time.Instant;
import java.util.Objects;

/**
 * Created by Elec332 on 25/04/2021
 */
public class PS2Player implements IPlayer {

    private long character_id;
    @SerializedName("name.first")
    private String name;
    private IPS2ObjectReference<IFaction> faction_id;
    @SerializedName("times.creation")
    private Instant creation;
    @SerializedName("times.last_save")
    private Instant last_save;
    @SerializedName("times.last_login")
    private Instant last_login;
    @SerializedName("battle_rank.value")
    private int battleRank;
    private int prestige_level;
    @SerializedName("outfit_member.outfit_id")
    private IPS2ObjectReference<IOutfit> outfit;

    @Override
    public long getId() {
        return this.character_id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public IFaction getFaction() {
        return this.faction_id.getObject();
    }

    @Override
    public Instant getCreationDate() {
        return this.creation;
    }

    @Override
    public Instant getLastLoginDate() {
        return this.last_login;
    }

    @Override
    public Instant getLastSaveDate() {
        return this.last_save;
    }

    @Override
    public int getBattleRank() {
        return this.battleRank;
    }

    @Override
    public boolean hasASP() {
        return prestige_level == 1;
    }

    @Override
    public IOutfit getOutfit() {
        return this.outfit.getObject();
    }

    //Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2Player ps2Player = (PS2Player) o;
        return character_id == ps2Player.character_id && battleRank == ps2Player.battleRank && prestige_level == ps2Player.prestige_level && Objects.equals(name, ps2Player.name) && Objects.equals(faction_id, ps2Player.faction_id) && Objects.equals(creation, ps2Player.creation) && Objects.equals(last_save, ps2Player.last_save) && Objects.equals(last_login, ps2Player.last_login) && Objects.equals(outfit.getId(), ps2Player.outfit.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(character_id, name, faction_id, creation, last_save, last_login, battleRank, prestige_level, outfit.getId());
    }

    @Override
    public String toString() {
        return "PS2Player{" +
                "character_id=" + character_id +
                ", name='" + name + '\'' +
                ", faction_id=" + faction_id +
                ", creation=" + creation +
                ", last_save=" + last_save +
                ", last_login=" + last_login +
                ", battleRank=" + battleRank +
                ", prestige_level=" + prestige_level +
                ", outfit_id=" + outfit.getId() +
                '}';
    }

}
