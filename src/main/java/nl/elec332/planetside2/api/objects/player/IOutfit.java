package nl.elec332.planetside2.api.objects.player;

import nl.elec332.planetside2.api.objects.registry.IPS2Object;
import nl.elec332.planetside2.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.api.objects.world.IFaction;
import nl.elec332.planetside2.api.objects.world.IServer;

import java.time.Instant;
import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by Elec332 on 23/04/2021
 */
public interface IOutfit extends IPS2Object {

    @Override
    long getId();

    @Override
    String getName();

    String getTag();

    Instant getCreationTime();

    IPS2ObjectReference<IFaction> getFaction();

    IPS2ObjectReference<IServer> getServer();

    int getMembers();

    String getRankName(int rank);

    Collection<IOutfitMember> getOutfitMemberInfo();

    IOutfitMember getMemberInfo(long playerId);

    Stream<IOutfitMember> getOnlineMembers();

    Stream<Map.Entry<IOutfitMember, IServer>> getOnlineMemberServers();

    Collection<Long> getPlayerIds(Predicate<IOutfitMember> filter);

    Collection<IPlayer> getPlayers(Predicate<IOutfitMember> filter);

}
