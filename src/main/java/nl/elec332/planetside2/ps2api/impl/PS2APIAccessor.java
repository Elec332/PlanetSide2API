package nl.elec332.planetside2.ps2api.impl;

import nl.elec332.planetside2.ps2api.api.ICensusAPI;
import nl.elec332.planetside2.ps2api.api.IPS2APIAccessor;
import nl.elec332.planetside2.ps2api.api.objects.IPS2API;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2Object;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2ObjectManager;
import nl.elec332.planetside2.ps2api.api.streaming.IStreamEventPoller;
import nl.elec332.planetside2.ps2api.api.streaming.IStreamingService;
import nl.elec332.planetside2.ps2api.api.streaming.request.IEventServiceFactory;
import nl.elec332.planetside2.ps2api.impl.streaming.PS2StreamEventPoller;
import nl.elec332.planetside2.ps2api.impl.streaming.PS2StreamingService;
import nl.elec332.planetside2.ps2api.impl.streaming.request.EventServiceFactory;
import nl.elec332.planetside2.ps2api.util.census.CensusAPI;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Elec332 on 30/04/2021
 */
public class PS2APIAccessor implements IPS2APIAccessor {

    public static IPS2APIAccessor INSTANCE = new PS2APIAccessor();

    private static final Object SYNC = new Object();
    private static String sid = "s:example";
    private static PS2APIImpl instance = null;
    private static boolean locked = false;
    private static boolean started = false;
    private static CensusAPI censusAPI = null;
    private static Set<IStreamingService> streamingServices;
    private static IStreamEventPoller streamEventPoller;

    private static void init() {
        if (!started) {
            locked = true;
            started = true;
            if (censusAPI != null && !censusAPI.matchesSID(sid)) {
                censusAPI = null;
            }
            getCensusAPI_();
            instance = new PS2APIImpl(censusAPI);
            streamingServices = new HashSet<>();
            streamEventPoller = new PS2StreamEventPoller(censusAPI);
        }
    }

    @Override
    public void setServiceId(String sid) {
        if (locked || instance != null) {
            throw new IllegalStateException();
        }
        PS2APIAccessor.sid = Objects.requireNonNull(sid);
    }

    private static ICensusAPI getCensusAPI_() {
        if (censusAPI == null) {
            censusAPI = new CensusAPI(sid);
        }
        return censusAPI;
    }

    @Override
    public ICensusAPI getCensusAPI() {
        return getCensusAPI_();
    }

    @Override
    public IPS2API getAPI() {
        synchronized (SYNC) {
            init();
            return instance;
        }
    }

    @Override
    public IStreamingService createStreamingService() {
        synchronized (SYNC) {
            init();
            try {
                IStreamingService ret = new PS2StreamingService(sid);
                streamingServices.add(ret);
                return ret;
            } catch (Exception e) {
                throw new RuntimeException("Failed to create new streaming service!", e);
            }
        }
    }

    @Override
    public IStreamEventPoller getStreamEventPoller() {
        synchronized (SYNC) {
            init();
            return streamEventPoller;
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
            started = false;
            streamingServices.forEach(IStreamingService::stop);
            streamingServices.clear();
            streamingServices = null;
            streamEventPoller = null;
        }
    }

    @Override
    public IEventServiceFactory getEventServiceFactory() {
        return EventServiceFactory.INSTANCE;
    }

    public static <T extends IPS2Object> IPS2ObjectManager<T> getManagerEarly(Class<T> type) {
        if (!started) {
            throw new IllegalStateException();
        }
        return PS2APIImpl.getManagerEarly(type);
    }

}
