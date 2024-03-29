package nl.elec332.planetside2.ps2api.util;

import com.google.gson.*;
import nl.elec332.planetside2.ps2api.api.IPS2APIAccessor;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.ps2api.api.streaming.event.base.IStreamingEvent;
import nl.elec332.planetside2.ps2api.impl.PS2APIAccessor;

import javax.net.ssl.SSLException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * Created by Elec332 on 23/04/2021
 */
public class NetworkUtil {

    public static Gson GSON = new GsonBuilder()
            .setLenient()
            .setPrettyPrinting()
            .registerTypeAdapter(IPS2ObjectReference.class, new PS2ObjectDeserializer())
            .registerTypeAdapter(IStreamingEvent.class, new StreamingEventDeserializer())
            .registerTypeAdapter(Instant.class, new TimeSecondDeserializer())
            .create();

    public static IPS2APIAccessor getAPIAccessor() {
        return PS2APIAccessor.INSTANCE;
    }

    @SuppressWarnings("all")
    public static JsonObject readJsonFromURL(String url_, boolean flatten) {
        String print = url_;
        int lenght = url_.length();
        if (lenght > 700) {
            print = url_.substring(0, 350) + " ... " + url_.substring(lenght - 350, lenght);
        }
        URL url;
        try {
            url = new URL(url_);
        } catch (Exception e) {
            throw new RuntimeException("Invalid URL!");
        }
        int f = 0;
        while (true) {
            try {
                InputStream is = url.openStream();
                try {
                    BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                    String s = rd.lines().collect(Collectors.joining("\n")).replace(":\"-\"", ":{}");
                    if (s.contains("<html>")) {
                        throw new RuntimeException("HTML bullshit");
                    }
                    JsonObject o;
                    try {
                        o = GSON.fromJson(s, JsonObject.class);
                    } catch (Exception e) {
                        System.out.println("Error JSON url: " + url_);
                        System.out.println(s);
                        throw e;
                    }
                    if (flatten) {
                        flattenJsonSelectively(o);
                        //System.out.println(GSON.toJson(o));
                    }
                    return o;
                } finally {
                    is.close();
                }
            } catch (Exception e) {
                if (f < 2) {
                    f++;
                    try {
                        Thread.sleep(100);
                    } catch (Exception ex) {
                        //nbc
                    }
                    continue;
                }
                System.out.println("API call: " + print);
                if (e instanceof ConnectException) {
                    throw new RuntimeException("Failed to connect to: " + url.getHost());
                }
                if (e instanceof SSLException) {
                    throw new RuntimeException("Could not connect due to SSL issues.", e);
                }
                if (e instanceof SocketException) {
                    throw new RuntimeException("SocketException caught, reason: " + e.getMessage());
                }
                throw new RuntimeException("Failed to poke API! (" + e.getMessage() + ")", e);
            }
        }
    }

    public static void flattenJsonSelectively(JsonElement je) {
        if (je.isJsonObject()) {
            JsonObject o = je.getAsJsonObject();
            for (String s : new HashSet<>(o.keySet())) {
                JsonElement e = o.get(s);
                if (s.contains("_full")) {
                    continue;
                }
                if (s.contains("_map")) {
                    if (e.isJsonObject()) {
                        squashMap(e.getAsJsonObject());
                    }
                    continue;
                }
                if (s.contains("_list") && e.isJsonArray()) {
                    squashList(e.getAsJsonArray());
                }
                if (e.isJsonObject()) {
                    JsonObject sup = e.getAsJsonObject();
                    flattenJsonSelectively(sup);
                    o.remove(s);
                    if (!s.contains("_hfull")) {
                        sup.entrySet().forEach(el -> o.add(s + "." + el.getKey(), el.getValue()));
                    }
                } else {
                    flattenJsonSelectively(e);
                }
            }
        } else if (je.isJsonArray()) {
            JsonArray a = je.getAsJsonArray();
            for (int i = 0; i < a.size(); i++) {
                flattenJsonSelectively(a.get(i));
            }
        }
    }

    private static void squashList(JsonArray array) {
        for (int i = 0; i < array.size(); i++) {
            JsonElement e = array.get(i);
            if (e.isJsonObject()) {
                JsonObject o = e.getAsJsonObject();
                if (o.keySet().size() == 1) {
                    array.set(i, o.get(o.keySet().iterator().next()));
                }
            }
        }
    }

    private static void squashMap(JsonObject object) {
        for (String s : object.keySet()) {
            JsonElement je = object.get(s);
            if (je.isJsonObject()) {
                JsonObject jo = je.getAsJsonObject();
                object.add(s, squashObject(jo));
            }
            flattenJsonSelectively(je);
        }
    }

    private static JsonElement squashObject(JsonObject jo) {
        if (jo.keySet().size() == 1) {
            JsonElement e = jo.get(jo.keySet().iterator().next());
            if (e.isJsonObject()) {
                return squashObject(e.getAsJsonObject());
            }
            return e;
        }
        return jo;
    }

    public static String toURLString(String s) {
        return s.replace(" ", "%20").replace("'", "%27");
    }

}
