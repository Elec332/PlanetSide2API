package nl.elec332.planetside2.api.streaming.event;

import nl.elec332.planetside2.api.objects.world.IFaction;
import nl.elec332.planetside2.api.streaming.event.base.IStreamingEvent;

/**
 * Created by Elec332 on 01/05/2021
 */
public interface IContinentStateEvent extends IStreamingEvent {

    int getNCPopulation();

    int getTRPopulation();

    int getVSPopulation();

    IFaction getPreviousFaction();

    IFaction getTriggeringFaction();

    int getPopulation(IFaction faction);

}
