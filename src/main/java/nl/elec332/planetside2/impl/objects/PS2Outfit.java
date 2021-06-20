package nl.elec332.planetside2.impl.objects;

import com.google.gson.annotations.SerializedName;
import nl.elec332.planetside2.api.objects.player.IOutfit;
import nl.elec332.planetside2.api.objects.player.IOutfitMember;
import nl.elec332.planetside2.api.objects.player.IPlayer;
import nl.elec332.planetside2.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.api.objects.world.IFaction;
import nl.elec332.planetside2.api.objects.world.IServer;
import nl.elec332.planetside2.impl.PS2APIAccessor;
import nl.elec332.planetside2.impl.objects.misc.PS2OutfitMember;
import nl.elec332.planetside2.util.NetworkUtil;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private transient boolean membersFiltered = false;

    private Map<Long, PS2OutfitMember> getMemberMap() {
        if (!membersFiltered) {
            for (long l : new HashSet<>(member_map.keySet())) {
                PS2OutfitMember m = member_map.get(l);
                if (m == null || m.getPlayerId() == 0) {
                    member_map.remove(l);
                }
            }
            member_map.remove(null);
            member_map.remove(0L);
            membersFiltered = true;
        }
        return member_map;
    }

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
        return (Collection) this.getMemberMap().values();
    }

    @Override
    public IOutfitMember getMemberInfo(long playerId) {
        return this.getMemberMap().get(playerId);
    }

    @Override
    public Stream<Map.Entry<IOutfitMember, IServer>> getOnlineMemberServers() {
        OnlineStatus status = getOnlineStatus();
        return this.getOutfitMemberInfo().stream()
                .filter(m -> status.member_map.get(m.getPlayerId()) > 0)
                .map(m -> new AbstractMap.SimpleEntry<>(m, PS2APIAccessor.INSTANCE.getAPI().getServers().get(status.member_map.get(m.getPlayerId()))));
    }

    @Override
    public Stream<IOutfitMember> getOnlineMembers() {
        OnlineStatus status = getOnlineStatus();
        try {
            return this.getOutfitMemberInfo()
                    .stream()
                    .filter(m -> {
                        Integer r = status.member_map.get(m.getPlayerId());
                        return r != null && r > 0;
                    });
        } catch (Exception e) {
            System.out.println(status);
            System.out.println(status.member_map);
            System.out.println(this.getOutfitMemberInfo());
            System.out.println(this.getOutfitMemberInfo()
                    .stream()
                    .filter(Objects::nonNull)
                    .filter(m -> status.member_map.get(m.getPlayerId()) > 0)
                    .collect(Collectors.toList()));
            throw new RuntimeException(e);
        }
    }

    private OnlineStatus getOnlineStatus() {
        return NetworkUtil.GSON.fromJson(PS2APIAccessor.INSTANCE.getCensusAPI().requestSingleObject("outfit", "outfit_id=" + outfit_id + "&c:join=outfit_member^list:1^inject_at:member_map^show:character_id(characters_online_status^on:character_id^inject_at:online^show:online_status)&c:tree=start:member_map^field:character_id"), OnlineStatus.class);
    }

    private static class OnlineStatus implements Serializable {

        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
        Map<Long, Integer> member_map;

    }

    @Override
    public Collection<Long> getPlayerIds(Predicate<IOutfitMember> filter) {
        return this.getMemberMap().values().stream()
                .filter(filter)
                .map(PS2OutfitMember::getPlayerId)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<IPlayer> getPlayers(Predicate<IOutfitMember> filter) {
        return this.getMemberMap().values().stream()
                .filter(filter)
                .map(PS2OutfitMember::getPlayerId)
                .map(PS2APIAccessor.INSTANCE.getAPI().getPlayerManager()::get)
                .collect(Collectors.toList());
    }

    //Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PS2Outfit ps2Outfit = (PS2Outfit) o;
        return outfit_id == ps2Outfit.outfit_id && member_count == ps2Outfit.member_count && Objects.equals(name, ps2Outfit.name) && Objects.equals(alias, ps2Outfit.alias) && Objects.equals(time_created, ps2Outfit.time_created) && Objects.equals(faction, ps2Outfit.faction) && Objects.equals(world, ps2Outfit.world) && Objects.equals(rank_map, ps2Outfit.rank_map) && Objects.equals(getMemberMap(), ps2Outfit.getMemberMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(outfit_id, name, alias, time_created, member_count, faction, world, rank_map, getMemberMap());
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
                ", member_map=" + getMemberMap() +
                '}';
    }

}
