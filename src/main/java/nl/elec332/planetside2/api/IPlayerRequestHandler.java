package nl.elec332.planetside2.api;

import nl.elec332.planetside2.api.player.IPlayerResponseList;
import nl.elec332.planetside2.api.player.request.ICharacterStat;
import nl.elec332.planetside2.api.player.request.ICharacterStatHistory;

import java.util.Collection;

/**
 * Created by Elec332 on 26/04/2021
 */
public interface IPlayerRequestHandler {

    String[] EMPTY_STRING_ARRAY = new String[0];

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
