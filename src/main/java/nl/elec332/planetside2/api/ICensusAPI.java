package nl.elec332.planetside2.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Created by Elec332 on 30/04/2021
 */
public interface ICensusAPI {

    JsonArray invokeAPI(String root, String command);

    JsonObject requestSingleObject(String root, String command);

}
