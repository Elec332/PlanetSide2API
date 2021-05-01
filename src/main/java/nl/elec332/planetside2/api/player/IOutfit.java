package nl.elec332.planetside2.api.player;

import nl.elec332.planetside2.api.registry.IPS2Object;
import nl.elec332.planetside2.api.registry.IPS2ObjectReference;
import nl.elec332.planetside2.api.world.IFaction;
import nl.elec332.planetside2.api.world.IServer;

import java.time.Instant;
import java.util.Collection;
import java.util.function.Predicate;

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

    Collection<Long> getPlayerIds(Predicate<IOutfitMember> filter);

    Collection<IPlayer> getPlayers(Predicate<IOutfitMember> filter);

}
