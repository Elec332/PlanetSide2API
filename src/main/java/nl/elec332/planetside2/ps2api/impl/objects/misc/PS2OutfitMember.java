package nl.elec332.planetside2.ps2api.impl.objects.misc;

import com.google.gson.annotations.SerializedName;
import nl.elec332.planetside2.ps2api.api.objects.player.IOutfit;
import nl.elec332.planetside2.ps2api.api.objects.player.IOutfitMember;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2ObjectReference;

import java.time.Instant;
import java.util.Objects;

/**
 * Created by Elec332 on 25/04/2021
 */
public class PS2OutfitMember implements IOutfitMember {

    @SerializedName("character_id")
    private long character;
    @SerializedName("name.first")
    private String name;
    @SerializedName("times.last_save")
    private Instant activity;
    @SerializedName("outfit_info.outfit_id")
    private IPS2ObjectReference<IOutfit> outfit;
    @SerializedName("outfit_info.member_since")
    private Instant joined;
    @SerializedName("outfit_info.rank_ordinal")
    private int rank;

    @Override
    public long getPlayerId() {
        return this.character;
    }

    @Override
    public String getPlayerName() {
        return this.name;
    }

    @Override
    public IOutfit getOutfit() {
        return this.outfit.getObject();
    }

    @Override
    public Instant getLastPlayerActivity() {
        return this.activity;
    }

    @Override
    public Instant getJoinDate() {
        return this.joined;
    }

    @Override
    public int getRankIndex() {
        return this.rank;
    }

    //Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2OutfitMember that = (PS2OutfitMember) o;
        return character == that.character && rank == that.rank && Objects.equals(name, that.name) && Objects.equals(activity, that.activity) && Objects.equals(outfit.getId(), that.outfit.getId()) && Objects.equals(joined, that.joined);
    }

    @Override
    public int hashCode() {
        return Objects.hash(character, name, activity, outfit, joined, rank);
    }

    @Override
    public String toString() {
        return "PS2OutfitMember{" +
                "character=" + character +
                ", name='" + name + '\'' +
                ", activity=" + activity +
                ", outfit=" + outfit.getObject().getName() +
                ", joined=" + joined +
                ", rank=" + rank +
                '}';
    }

}
