package nl.elec332.planetside2.ps2api.api.objects;

import nl.elec332.planetside2.ps2api.api.objects.misc.IItemSet;
import nl.elec332.planetside2.ps2api.api.objects.player.IPlayerRequestList;
import nl.elec332.planetside2.ps2api.api.objects.player.IPlayerResponseList;
import nl.elec332.planetside2.ps2api.api.objects.player.ISlimPlayer;
import nl.elec332.planetside2.ps2api.api.objects.player.request.ICharacterStat;
import nl.elec332.planetside2.ps2api.api.objects.player.request.ICharacterStatHistory;
import nl.elec332.planetside2.ps2api.api.objects.player.request.IFactionWeaponStat;

import java.util.Collection;

/**
 * Created by Elec332 on 26/04/2021
 */
public interface IPlayerRequestHandler {

    String[] EMPTY_STRING_ARRAY = new String[0];

    Collection<ISlimPlayer> getSlimPlayers(Collection<Long> players);

    IPlayerResponseList<IPlayerRequestList<IFactionWeaponStat>> getSlimCharacterWeaponStats(Collection<Long> players, IItemSet itemSet);

    default IPlayerResponseList<IPlayerRequestList<IFactionWeaponStat>> getCharacterWeaponStats(Collection<Long> players, IItemSet itemSet) {
        return getCharacterWeaponStats(players, itemSet, EMPTY_STRING_ARRAY);
    }

    IPlayerResponseList<IPlayerRequestList<IFactionWeaponStat>> getCharacterWeaponStats(Collection<Long> players, IItemSet itemSet, String... stats);

    default IPlayerResponseList<IPlayerRequestList<ICharacterStat>> getSlimCharacterStats(Collection<Long> players) {
        return getSlimCharacterStats(players, EMPTY_STRING_ARRAY);
    }

    IPlayerResponseList<IPlayerRequestList<ICharacterStat>> getSlimCharacterStats(Collection<Long> players, String... stats);

    default IPlayerResponseList<IPlayerRequestList<ICharacterStat>> getCharacterStats(Collection<Long> players) {
        return getCharacterStats(players, EMPTY_STRING_ARRAY);
    }

    IPlayerResponseList<IPlayerRequestList<ICharacterStat>> getCharacterStats(Collection<Long> players, String... stats);

    default IPlayerResponseList<IPlayerRequestList<ICharacterStatHistory>> getSlimCharacterStatHistory(Collection<Long> players) {
        return getSlimCharacterStatHistory(players, EMPTY_STRING_ARRAY);
    }

    IPlayerResponseList<IPlayerRequestList<ICharacterStatHistory>> getSlimCharacterStatHistory(Collection<Long> players, String... stats);

    default IPlayerResponseList<IPlayerRequestList<ICharacterStatHistory>> getCharacterStatHistory(Collection<Long> players) {
        return getCharacterStatHistory(players, EMPTY_STRING_ARRAY);
    }

    IPlayerResponseList<IPlayerRequestList<ICharacterStatHistory>> getCharacterStatHistory(Collection<Long> players, String... stats);

}
