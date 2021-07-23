package nl.elec332.planetside2.ps2api.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by Elec332 on 30/04/2021
 */
public interface ICensusAPI {

    void disableSSL();

    boolean matchesSID(String sid);

    JsonArray invokeAPI(String root, String command);

    <T> Stream<T> invokeAPI(String root, String command, Class<T> type);

    <T> Stream<T> invokeAPI(String root, String command, Function<JsonElement, T> deserializer);

    JsonObject requestSingleObject(String root, String command);

}
