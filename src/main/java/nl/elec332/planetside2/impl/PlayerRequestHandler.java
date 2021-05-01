package nl.elec332.planetside2.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import nl.elec332.planetside2.api.ICensusAPI;
import nl.elec332.planetside2.api.IPlayerRequestHandler;
import nl.elec332.planetside2.api.player.IPlayerRequest;
import nl.elec332.planetside2.api.player.IPlayerResponseList;
import nl.elec332.planetside2.api.player.request.ICharacterStat;
import nl.elec332.planetside2.api.player.request.ICharacterStatHistory;
import nl.elec332.planetside2.api.player.request.IStat;
import nl.elec332.planetside2.api.registry.IPS2ObjectReference;
import nl.elec332.planetside2.api.world.IFaction;
import nl.elec332.planetside2.impl.objects.misc.PS2CharacterStat;
import nl.elec332.planetside2.impl.objects.misc.PS2CharacterStatHistory;
import nl.elec332.planetside2.util.census.CensusRequestBuilder;
import nl.elec332.planetside2.util.NetworkUtil;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by Elec332 on 26/04/2021
 */
public class PlayerRequestHandler implements IPlayerRequestHandler {

    public PlayerRequestHandler(ICensusAPI api) {
        this.api = api;
    }

    private final ICensusAPI api;

    @Override
    public IPlayerResponseList<ICharacterStat> getCharacterStats(Collection<Long> players, String... stats) {
        return getStats(players, "characters_stat", null, PS2CharacterStat.class, stats);
    }

    @Override
    public IPlayerResponseList<ICharacterStat> getSlimCharacterStats(Collection<Long> players, String... stats) {
        return getStats(players, "characters_stat", j -> j.show("stat_name", "value_daily", "value_weekly", "value_monthly", "profile_id"), PS2CharacterStat.class, stats);
    }

    @Override
    public IPlayerResponseList<ICharacterStatHistory> getSlimCharacterStatHistory(Collection<Long> players, String... stats) {
        return getStats(players, "characters_stat_history", j -> j.show("stat_name", "all_time", "day.d01", "week.w01", "month.m01"), PS2CharacterStatHistory.class, stats);
    }

    @Override
    public IPlayerResponseList<ICharacterStatHistory> getCharacterStatHistory(Collection<Long> players, String... stats) {
        return getStats(players, "characters_stat_history", null, PS2CharacterStatHistory.class, stats);
    }

    private <R, T extends R> IPlayerResponseList<R> getStats(Collection<Long> ids, String join, Consumer<CensusRequestBuilder.JoinBuilder> builder, Class<T> response, String[] stats) {
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

    private <R, T extends R> List<IPlayerRequest<R>> makeRequest(Collection<Long> ids, String join, Consumer<CensusRequestBuilder.JoinBuilder> builder, boolean exploded, Class<T> response) {
        String inject = "result" + (exploded ? "_full" : "");
        JsonArray players = makeRequest(ids, join, builder, inject);
//        System.out.println(NetworkUtil.GSON.toJson(players));
//        System.exit(0);
        List<IPlayerRequest<R>> ret = new ArrayList<>();
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
                //resp.add(NetworkUtil.GSON.fromJson(je, response));
            }
            ret.add(new Response<>(jo.get("character_id").getAsLong(), jo.get("name.first").getAsString(), jo.get("faction_id").getAsInt(), resp));
        }
        return ret;
    }

    private JsonArray makeRequest(Collection<Long> ids, String join, Consumer<CensusRequestBuilder.JoinBuilder> builder, String inject) {
        CensusRequestBuilder requestBuilder = new CensusRequestBuilder();
        requestBuilder.objects("character_id", ids)
                .show("character_id", "name.first", "faction_id")
                .join(join, j -> {
                    if (builder != null) {
                        builder.accept(j);
                    }
                    j.asList().injectAt(inject);
                });
        return api.invokeAPI("character", requestBuilder.build());
    }

    private static class ResponseList<T> implements IPlayerResponseList<T> {

        private ResponseList(List<IPlayerRequest<T>> response) {
            this.response = Collections.unmodifiableList(response);
        }

        private final List<IPlayerRequest<T>> response;

        @Override
        public List<IPlayerRequest<T>> getAsList() {
            return this.response;
        }

    }

    private static class Response<T> implements IPlayerRequest<T> {

        @SuppressWarnings("unchecked")
        private Response(long playerId, String playerName, int faction, List<T> response) {
            this.playerId = playerId;
            this.playerName = playerName;
            this.response = Collections.unmodifiableList(response);
            this.faction = PS2APIAccessor.INSTANCE.getAPI().getFactions().getReference(faction);
            this.namedResponse = this.response.iterator().next() instanceof IStat ?
                    this.response.stream().map(t -> (IStat) t).collect(Collectors.toMap(IStat::getStatName, t -> (T) t))
                    : null;
        }

        private final long playerId;
        private final String playerName;
        private final IPS2ObjectReference<? extends IFaction> faction;
        private final List<T> response;
        private final Map<String, T> namedResponse;

        @Override
        public long getPlayerId() {
            return this.playerId;
        }

        @Override
        public String getName() {
            return this.playerName;
        }

        @Override
        public IFaction getPlayerFaction() {
            return this.faction.getObject();
        }

        @Override
        public Collection<T> getResponse() {
            return this.response;
        }

        @Override
        public T getFirstResponse() {
            return this.response.get(0);
        }

        @Override
        public T getResponseByName(String name) {
            return this.namedResponse == null ? null : this.namedResponse.get(name);
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
