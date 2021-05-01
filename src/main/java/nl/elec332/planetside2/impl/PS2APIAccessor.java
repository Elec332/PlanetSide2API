package nl.elec332.planetside2.impl;

import nl.elec332.planetside2.api.ICensusAPI;
import nl.elec332.planetside2.api.IPS2API;
import nl.elec332.planetside2.api.IPS2APIAccessor;
import nl.elec332.planetside2.api.registry.IPS2Object;
import nl.elec332.planetside2.api.registry.IPS2ObjectManager;
import nl.elec332.planetside2.util.census.CensusAPI;

import java.util.Objects;

/**
 * Created by Elec332 on 30/04/2021
 */
public class PS2APIAccessor implements IPS2APIAccessor {

    public static IPS2APIAccessor INSTANCE = new PS2APIAccessor();

    private static final Object SYNC = new Object();
    private static String sid = "s:example";
    private static PS2APIImpl instance = null;
    private static boolean started = false;
    private static CensusAPI censusAPI = null;

    @Override
    public void setServiceId(String sid) {
        if (instance != null) {
            throw new IllegalStateException();
        }
        PS2APIAccessor.sid = Objects.requireNonNull(sid);
    }

    @Override
    public ICensusAPI getCensusAPI() {
        if (censusAPI == null) {
            getAPI();
        }
        return censusAPI;
    }

    @Override
    public IPS2API getAPI() {
        synchronized (SYNC) {
            if (!started) {
                started = true;
                censusAPI = new CensusAPI(sid);
                instance = new PS2APIImpl(censusAPI);
            }
            return instance;
        }
    }

    @Override
    public void stopAPI() {
        synchronized (SYNC) {
            if (instance == null) {
                throw new IllegalStateException();
            }
            instance.stop();
            instance = null;
            censusAPI = null;
            started = false;
        }
    }

    public static <T extends IPS2Object> IPS2ObjectManager<T> getManagerEarly(Class<T> type) {
        if (!started) {
            throw new IllegalStateException();
        }
        return PS2APIImpl.getManagerEarly(type);
    }

}
