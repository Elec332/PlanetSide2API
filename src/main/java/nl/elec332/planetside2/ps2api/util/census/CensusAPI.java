package nl.elec332.planetside2.ps2api.util.census;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import nl.elec332.planetside2.ps2api.api.ICensusAPI;
import nl.elec332.planetside2.ps2api.api.objects.IHasImage;
import nl.elec332.planetside2.ps2api.util.Constants;
import nl.elec332.planetside2.ps2api.util.NetworkUtil;

import java.io.InputStream;
import java.net.URL;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


/**
 * Created by Elec332 on 23/04/2021
 */
public class CensusAPI implements ICensusAPI {

    public CensusAPI(String serviceId) {
        this.sid = serviceId;
    }

    private static final String RETURNED = "returned";
    private final String sid;
    private boolean ssl = true;

    /**
     * Invokes a call to the Planetside2 Census API
     */
    private JsonObject invokeAPI_(String root, String command) {
        return NetworkUtil.readJsonFromURL(getURLBase() + sid + "/get/ps2:v2/" + root + "/?" + NetworkUtil.toURLString(command), true);
    }

    @Override
    public void disableSSL() {
        this.ssl = false;
    }

    @Override
    public boolean matchesSID(String sid) {
        return this.sid.equals(sid);
    }

    @Override
    public JsonArray invokeAPI(String root, String command) {
        return checkAPI(invokeAPI_(root, command));
    }

    @Override
    public <T> Stream<T> invokeAPI(String root, String command, Class<T> type) {
        return invokeAPI(root, command, o -> NetworkUtil.GSON.fromJson(o, type));
    }

    @Override
    public <T> Stream<T> invokeAPI(String root, String command, Function<JsonElement, T> deserializer) {
        return StreamSupport.stream(invokeAPI(root, command).spliterator(), false).map(deserializer);
    }

    @Override
    public JsonObject requestSingleObject(String root, String command) {
        JsonArray data = checkAPI(invokeAPI_(root, command));
        if (data.size() == 1) {
            return data.get(0).getAsJsonObject();
        }
        if (data.size() == 0) {
            return null;
        }
        throw new UnsupportedOperationException("Returned object count is " + data.size());
    }

    @Override
    public InputStream getImage(IHasImage img) {
        int id = img.getImageId();
        if (id < 0) {
            throw new RuntimeException("Invalid image: " + img);
        }
        try {
            URL url = new URL(getURLBase() + "files/ps2/images/static/" + id + ".png");
            return url.openStream();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getURLBase() {
        return "http" + (ssl ? "s" : "") + "://census.daybreakgames.com/";
    }

    /**
     * Returns the result from a Planetside 2 Census API call
     *
     * @param jo The JSON object returned by Census
     * @return The result of the API call
     */
    private static JsonArray checkAPI(JsonObject jo) {
        if (jo.has(RETURNED)) {
            int len = jo.get(RETURNED).getAsInt();
            JsonArray ret = jo.getAsJsonArray(jo.keySet().stream().filter(s -> !s.equals(RETURNED)).findFirst().orElseThrow(NullPointerException::new));
            if (len >= Constants.API_MAX_RETURN_SIZE) {
                throw new RuntimeException("API return information was incomplete! (above the max of 5000 values)");
            }
            if (len == ret.size()) {
                return ret;
            }
        }
        if (jo.has("error")) {
            String error = jo.get("error").getAsString();
            throw new RuntimeException("API returned error: " + error);
        }
        throw new RuntimeException("API didn't return expected result!");
    }

}
