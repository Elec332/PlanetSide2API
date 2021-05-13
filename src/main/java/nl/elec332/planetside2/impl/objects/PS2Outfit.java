package nl.elec332.planetside2.impl.objects;

import com.google.gson.annotations.SerializedName;
import nl.elec332.planetside2.api.objects.player.IOutfit;
import nl.elec332.planetside2.api.objects.player.IOutfitMember;
import nl.elec332.planetside2.api.objects.player.IPlayer;
import nl.elec332.planetside2.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.api.objects.world.IFaction;
import nl.elec332.planetside2.api.objects.world.IServer;
import nl.elec332.planetside2.impl.objects.misc.PS2OutfitMember;

import java.time.Instant;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Elec332 on 23/04/2021
 */
public class PS2Outfit implements IOutfit {

    private long outfit_id;
    private String name;
    private String alias;
    private Instant time_created;
    private int member_count;
    @SerializedName("faction.faction_id")
    private IPS2ObjectReference<IFaction> faction;
    @SerializedName("world.world_id")
    private IPS2ObjectReference<IServer> world;
    private Map<Integer, String> rank_map;
    private Map<Long, PS2OutfitMember> member_map;

    @Override
    public long getId() {
        return this.outfit_id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getTag() {
        return this.alias;
    }

    @Override
    public Instant getCreationTime() {
        return time_created;
    }

    @Override
    public IPS2ObjectReference<IFaction> getFaction() {
        return this.faction;
    }

    @Override
    public IPS2ObjectReference<IServer> getServer() {
        return this.world;
    }

    @Override
    public int getMembers() {
        return this.member_count;
    }

    @Override
    public String getRankName(int rank) {
        return this.rank_map.get(rank);
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Collection<IOutfitMember> getOutfitMemberInfo() {
        return (Collection) this.member_map.values();
    }

    @Override
    public IOutfitMember getMemberInfo(long playerId) {
        return this.member_map.get(playerId);
    }

    @Override
    public Collection<Long> getPlayerIds(Predicate<IOutfitMember> filter) {
        return this.member_map.values().stream()
                .filter(filter)
                .map(PS2OutfitMember::getPlayerId)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<IPlayer> getPlayers(Predicate<IOutfitMember> filter) {
        return null;
    }

    //Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2Outfit ps2Outfit = (PS2Outfit) o;
        return outfit_id == ps2Outfit.outfit_id && member_count == ps2Outfit.member_count && Objects.equals(name, ps2Outfit.name) && Objects.equals(alias, ps2Outfit.alias) && Objects.equals(time_created, ps2Outfit.time_created) && Objects.equals(faction, ps2Outfit.faction) && Objects.equals(world, ps2Outfit.world) && Objects.equals(rank_map, ps2Outfit.rank_map) && Objects.equals(member_map, ps2Outfit.member_map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(outfit_id, name, alias, time_created, member_count, faction, world, rank_map, member_map);
    }

    @Override
    public String toString() {
        return "PS2Outfit{" +
                "outfit_id=" + outfit_id +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", time_created=" + time_created +
                ", member_count=" + member_count +
                ", faction=" + faction +
                ", world=" + world +
                ", rank_map=" + rank_map +
                ", member_map=" + member_map +
                '}';
    }

}
