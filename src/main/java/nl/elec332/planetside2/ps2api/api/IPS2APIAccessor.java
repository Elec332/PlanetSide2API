package nl.elec332.planetside2.ps2api.api;

import nl.elec332.planetside2.ps2api.api.objects.IPS2API;
import nl.elec332.planetside2.ps2api.api.streaming.IStreamEventPoller;
import nl.elec332.planetside2.ps2api.api.streaming.IStreamingService;
import nl.elec332.planetside2.ps2api.api.streaming.request.IEventServiceFactory;

/**
 * Created by Elec332 on 30/04/2021
 * <p>
 * Help, my eyes
 */
public interface IPS2APIAccessor {

    void setServiceId(String sid);

    ICensusAPI getCensusAPI();

    IPS2API getAPI();

    IStreamingService createStreamingService();

    IStreamEventPoller getStreamEventPoller();

    void stopAPI();

    //Static
    IEventServiceFactory getEventServiceFactory();

}
