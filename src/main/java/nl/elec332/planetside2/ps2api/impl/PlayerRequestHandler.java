package nl.elec332.planetside2.ps2api.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import nl.elec332.planetside2.ps2api.api.ICensusAPI;
import nl.elec332.planetside2.ps2api.api.objects.IPlayerRequestHandler;
import nl.elec332.planetside2.ps2api.api.objects.misc.IItemSet;
import nl.elec332.planetside2.ps2api.api.objects.player.IPlayerRequestList;
import nl.elec332.planetside2.ps2api.api.objects.player.IPlayerResponseList;
import nl.elec332.planetside2.ps2api.api.objects.player.ISlimPlayer;
import nl.elec332.planetside2.ps2api.api.objects.player.request.ICharacterStat;
import nl.elec332.planetside2.ps2api.api.objects.player.request.ICharacterStatHistory;
import nl.elec332.planetside2.ps2api.api.objects.player.request.IFactionWeaponStat;
import nl.elec332.planetside2.ps2api.api.objects.player.request.IStat;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.ps2api.api.objects.world.IFaction;
import nl.elec332.planetside2.ps2api.impl.objects.misc.PS2CharacterStat;
import nl.elec332.planetside2.ps2api.impl.objects.misc.PS2CharacterStatHistory;
import nl.elec332.planetside2.ps2api.impl.objects.misc.PS2FactionWeaponStat;
import nl.elec332.planetside2.ps2api.util.NetworkUtil;
import nl.elec332.planetside2.ps2api.util.census.CensusRequestBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Created by Elec332 on 26/04/2021
 */
public class PlayerRequestHandler implements IPlayerRequestHandler {

    public PlayerRequestHandler(ICensusAPI api) {
        this.api = api;
    }

    private final ICensusAPI api;

    @Override
    public Collection<ISlimPlayer> getSlimPlayers(Collection<Long> players) {
        JsonArray a = makeRequest(players, null, null, null);
        List<ISlimPlayer> ret = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            JsonObject jo = a.get(i).getAsJsonObject();
            ret.add(new SlimPlayer(jo.get("character_id").getAsLong(), jo.get("name.first").getAsString(), jo.get("faction_id").getAsInt()));
        }
        return ret;
    }

    @Override
    public IPlayerResponseList<IPlayerRequestList<IFactionWeaponStat>> getSlimCharacterWeaponStats(Collection<Long> players, IItemSet itemSet) {
        return getCharacterWeaponStats(players, itemSet, "weapon_kills", "weapon_killed_by", "weapon_vehicle_kills");
    }

    @Override
    public IPlayerResponseList<IPlayerRequestList<IFactionWeaponStat>> getCharacterWeaponStats(Collection<Long> players, IItemSet itemSet, String... stats) {
        return getStats(players, "characters_weapon_stat_by_faction", j -> {
            j.hide("character_id", "last_save_date");
            if (itemSet != null) {
                j.terms(c -> itemSet.getItems().forEach(i -> {
                    c.accept("item_id", i);
                }));
            }
        }, PS2FactionWeaponStat.class, stats);
    }

    @Override
    public IPlayerResponseList<IPlayerRequestList<ICharacterStat>> getCharacterStats(Collection<Long> players, String... stats) {
        return getStats(players, "characters_stat", null, PS2CharacterStat.class, stats);
    }

    @Override
    public IPlayerResponseList<IPlayerRequestList<ICharacterStat>> getSlimCharacterStats(Collection<Long> players, String... stats) {
        return getStats(players, "characters_stat", j -> j.show("stat_name", "value_daily", "value_weekly", "value_monthly", "profile_id"), PS2CharacterStat.class, stats);
    }

    @Override
    public IPlayerResponseList<IPlayerRequestList<ICharacterStatHistory>> getSlimCharacterStatHistory(Collection<Long> players, String... stats) {
        return getStats(players, "characters_stat_history", j -> j.show("stat_name", "all_time", "day.d01", "week.w01", "month.m01"), PS2CharacterStatHistory.class, stats);
    }

    @Override
    public IPlayerResponseList<IPlayerRequestList<ICharacterStatHistory>> getCharacterStatHistory(Collection<Long> players, String... stats) {
        return getStats(players, "characters_stat_history", null, PS2CharacterStatHistory.class, stats);
    }

    private <R, T extends R> IPlayerResponseList<IPlayerRequestList<R>> getStats(Collection<Long> ids, String join, Consumer<CensusRequestBuilder.JoinBuilder> builder, Class<T> response, String[] stats) {
        return new ResponseList<>(makeRequest(ids, join, j -> {
            if (stats == null || stats.length == 0) {
                return;
            }
            j.terms(t -> {
                for (String s : stats) {
                    t.accept("stat_name", s);
                }
            });
            if (builder != null) {
                builder.accept(j);
            }
        }, true, response));
    }

    private <R, T extends R> List<IPlayerRequestList<R>> makeRequest(Collection<Long> ids, String join, Consumer<CensusRequestBuilder.JoinBuilder> builder, boolean exploded, Class<T> response) {
        String inject = "result" + (exploded ? "_full" : "");
        JsonArray players = makeRequest(ids, join, builder, inject);
        List<IPlayerRequestList<R>> ret = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            List<R> resp = new ArrayList<>();
            JsonObject jo = players.get(i).getAsJsonObject();
            JsonElement je = jo.get(inject);
            if (je == null) {
                continue;
            }
            if (je.isJsonArray()) {
                JsonArray ja = je.getAsJsonArray();
                for (int j = 0; j < ja.size(); j++) {
                    resp.add(NetworkUtil.GSON.fromJson(ja.get(j), response));
                }
            } else {
                throw new UnsupportedOperationException();
            }
            ret.add(new Response<>(jo.get("character_id").getAsLong(), jo.get("name.first").getAsString(), jo.get("faction_id").getAsInt(), resp));
        }
        return ret;
    }

    private JsonArray makeRequest(Collection<Long> ids, String join, Consumer<CensusRequestBuilder.JoinBuilder> builder, String inject) {
        CensusRequestBuilder requestBuilder = new CensusRequestBuilder();
        requestBuilder.objects("character_id", ids)
                .show("character_id", "name.first", "faction_id");
        if (join != null) {
            requestBuilder.join(join, j -> {
                if (builder != null) {
                    builder.accept(j);
                }
                j.asList().injectAt(inject);
            });
        }

        return api.invokeAPI("character", requestBuilder.build());
    }

    private static class ResponseList<T extends ISlimPlayer> implements IPlayerResponseList<T> {

        private ResponseList(List<T> response) {
            this.response = Collections.unmodifiableList(response);
        }

        private final List<T> response;

        @Override
        public List<T> getAsList() {
            return this.response;
        }

    }

    private static class SlimPlayer implements ISlimPlayer {

        private SlimPlayer(long playerId, String playerName, int faction) {
            this.playerId = playerId;
            this.playerName = playerName;
            this.faction = PS2APIAccessor.INSTANCE.getAPI().getFactions().getReference(faction);
        }

        final long playerId;
        final String playerName;
        final IPS2ObjectReference<? extends IFaction> faction;

        @Override
        public long getId() {
            return this.playerId;
        }

        @Override
        public String getName() {
            return this.playerName;
        }

        @Override
        public IFaction getFaction() {
            return this.faction.getObject();
        }

        @Override
        public String toString() {
            return "SlimPlayer{" +
                    "playerId=" + playerId +
                    ", playerName='" + playerName + '\'' +
                    ", faction=" + faction +
                    '}';
        }

    }

    private static class Response<T> extends SlimPlayer implements IPlayerRequestList<T> {

        private Response(long playerId, String playerName, int faction, List<T> response) {
            super(playerId, playerName, faction);
            this.response = Collections.unmodifiableList(response);
            this.named = this.response.iterator().next() instanceof IStat;
        }

        private final List<T> response;
        private final boolean named;

        @Override
        public Collection<T> getResponse() {
            return this.response;
        }

        @Override
        public T getFirstResponse() {
            return this.response.get(0);
        }

        @Override
        public Stream<T> getResponseByName(String name) {
            return named ? response.stream().filter(t -> name.equalsIgnoreCase(((IStat) t).getStatName())) : null;
        }

        @Override
        public T getFirstResponseByName(String name) {
            return getResponseByName(name).findFirst().orElse(null);
        }

        @Override
        public String toString() {
            return "Response{" +
                    "playerId=" + playerId +
                    ", playerName='" + playerName + '\'' +
                    ", response=" + response +
                    '}';
        }

    }

}
