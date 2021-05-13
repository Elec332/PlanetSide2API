package nl.elec332.planetside2.impl.streaming.request;

import java.util.*;

/**
 * Created by Elec332 on 01/05/2021
 */
public final class EventServiceRequest {

    public static EventServiceRequest createBaseRequest(String action, Set<Long> characters, Set<Long> worlds, String eventName) {
        return createBaseRequest(action, true, characters, worlds, Collections.singleton(eventName));
    }

    public static EventServiceRequest createBaseRequest(String action, Set<Long> characters, Set<Long> worlds, Collection<String> eventNames) {
        return createBaseRequest(action, true, characters, worlds, eventNames);
    }

    @SuppressWarnings("SameParameterValue")
    private static EventServiceRequest createBaseRequest(String action, boolean charsAndWorldMatch, Set<Long> characters, Set<Long> worlds, Collection<String> eventNames) {
        EventServiceRequest ret = new EventServiceRequest();
        ret.backMap.put("service", "event");
        ret.backMap.put("action", action);
        ret.backMap.put("logicalAndCharactersWithWorlds", charsAndWorldMatch + "");
        String s;
        if (characters == null) {
            s = "[\"all\"]";
        } else {
            s = characters.toString();
        }
        ret.backMap.put("characters", s);
        if (worlds == null) {
            s = "[\"all\"]";
        } else {
            s = worlds.toString();
        }
        ret.backMap.put("worlds", s);
        ret.backMap.put("eventNames", "[\"" + String.join("\",\"", eventNames) + "\"]");
        return ret;
    }

    private EventServiceRequest() {
        this.backMap = new HashMap<>();
    }

    private final Map<String, String> backMap;

    public Map<String, String> toMap() {
        return Collections.unmodifiableMap(this.backMap);
    }

}
