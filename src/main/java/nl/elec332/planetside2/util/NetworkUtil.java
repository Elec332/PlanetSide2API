package nl.elec332.planetside2.util;

import com.google.gson.*;
import nl.elec332.planetside2.api.registry.IPS2ObjectReference;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashSet;

/**
 * Created by Elec332 on 23/04/2021
 */
public class NetworkUtil {

    public static Gson GSON = new GsonBuilder()
            .setLenient()
            .setPrettyPrinting()
            .registerTypeAdapter(IPS2ObjectReference.class, new PS2ObjectDeserializer())
            .registerTypeAdapter(Instant.class, new TimeSecondDeserializer())
            .create();

    @SuppressWarnings("all")
    public static JsonObject readJsonFromURL(String url_, boolean flatten) {
        String print = url_;
        int lenght = url_.length();
        if (lenght > 700) {
            print = url_.substring(0, 350) + " ... " + url_.substring(lenght - 350, lenght);
        }
        System.out.println("API call: " + print);
        URL url;
        try {
            url = new URL(url_);
        } catch (Exception e) {
            throw new RuntimeException("Invalid URL!");
        }
        try {
            InputStream is = url.openStream();
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                JsonObject o = GSON.fromJson(rd, JsonObject.class);
                if (flatten) {
                    flattenJsonSelectively(o);
                    //System.out.println(GSON.toJson(o));
                }
                return o;
            } finally {
                is.close();
            }
        } catch (Exception e) {
            if (e instanceof ConnectException) {
                throw new RuntimeException("Failed to connect to: " + url.getHost());
            }
            throw new RuntimeException("Failed to poke API! (" + e.getMessage() + ")", e);
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
            boolean b = false;
            if (je.isJsonObject()) {
                JsonObject jo = je.getAsJsonObject();
                if (jo.keySet().size() == 1) {
                    object.add(s, jo.get(jo.keySet().iterator().next()));
                    b = true;
                }
            }
            flattenJsonSelectively(je);
        }
    }

    public static String toURLString(String s) {
        return s.replace(" ", "%20").replace("'", "%27");
    }

}
