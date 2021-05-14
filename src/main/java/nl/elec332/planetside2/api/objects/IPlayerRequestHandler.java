package nl.elec332.planetside2.api.objects;

import nl.elec332.planetside2.api.objects.misc.IItemSet;
import nl.elec332.planetside2.api.objects.player.IPlayerResponseList;
import nl.elec332.planetside2.api.objects.player.request.ICharacterStat;
import nl.elec332.planetside2.api.objects.player.request.ICharacterStatHistory;
import nl.elec332.planetside2.api.objects.player.request.IFactionWeaponStat;

import java.util.Collection;

/**
 * Created by Elec332 on 26/04/2021
 */
public interface IPlayerRequestHandler {

    String[] EMPTY_STRING_ARRAY = new String[0];

    IPlayerResponseList<IFactionWeaponStat> getSlimCharacterWeaponStats(Collection<Long> players, IItemSet itemSet);

    default IPlayerResponseList<IFactionWeaponStat> getCharacterWeaponStats(Collection<Long> players, IItemSet itemSet) {
        return getCharacterWeaponStats(players, itemSet, EMPTY_STRING_ARRAY);
    }

    IPlayerResponseList<IFactionWeaponStat> getCharacterWeaponStats(Collection<Long> players, IItemSet itemSet, String... stats);

    default IPlayerResponseList<ICharacterStat> getSlimCharacterStats(Collection<Long> players) {
        return getSlimCharacterStats(players, EMPTY_STRING_ARRAY);
    }

    IPlayerResponseList<ICharacterStat> getSlimCharacterStats(Collection<Long> players, String... stats);

    default IPlayerResponseList<ICharacterStat> getCharacterStats(Collection<Long> players) {
        return getCharacterStats(players, EMPTY_STRING_ARRAY);
    }

    IPlayerResponseList<ICharacterStat> getCharacterStats(Collection<Long> players, String... stats);

    default IPlayerResponseList<ICharacterStatHistory> getSlimCharacterStatHistory(Collection<Long> players) {
        return getSlimCharacterStatHistory(players, EMPTY_STRING_ARRAY);
    }

    IPlayerResponseList<ICharacterStatHistory> getSlimCharacterStatHistory(Collection<Long> players, String... stats);

    default IPlayerResponseList<ICharacterStatHistory> getCharacterStatHistory(Collection<Long> players) {
        return getCharacterStatHistory(players, EMPTY_STRING_ARRAY);
    }

    IPlayerResponseList<ICharacterStatHistory> getCharacterStatHistory(Collection<Long> players, String... stats);

}
