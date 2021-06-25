package nl.elec332.planetside2.ps2api.impl.streaming.event;

import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2ObjectReference;
import nl.elec332.planetside2.ps2api.api.objects.world.IFaction;
import nl.elec332.planetside2.ps2api.api.streaming.event.IContinentStateEvent;
import nl.elec332.planetside2.ps2api.impl.streaming.event.base.AbstractEvent;

import java.io.Serializable;
import java.util.Locale;

/**
 * Created by Elec332 on 01/05/2021
 */
public class ContinentStateEvent extends AbstractEvent implements IContinentStateEvent, Serializable {

    private int nc_population, tr_population, vs_population;
    private IPS2ObjectReference<IFaction> previous_faction;
    private IPS2ObjectReference<IFaction> triggering_faction;

    @Override
    public int getNCPopulation() {
        return this.nc_population;
    }

    @Override
    public int getTRPopulation() {
        return this.tr_population;
    }

    @Override
    public int getVSPopulation() {
        return this.vs_population;
    }

    @Override
    public IFaction getPreviousFaction() {
        return this.previous_faction.getObject();
    }

    @Override
    public IFaction getTriggeringFaction() {
        return this.triggering_faction.getObject();
    }

    @Override
    public int getPopulation(IFaction faction) {
        String tag = faction.getTag().toLowerCase(Locale.ROOT);
        if (tag.equals("vs")) {
            return getVSPopulation();
        }
        if (tag.equals("nc")) {
            return getNCPopulation();
        }
        if (tag.equals("tr")) {
            return getTRPopulation();
        }
        return 0;
    }

    @Override
    public String toString() {
        return "ContinentStateEvent{" +
                "nc_population=" + nc_population +
                ", tr_population=" + tr_population +
                ", vs_population=" + vs_population +
                ", previous_faction=" + previous_faction +
                ", triggering_faction=" + triggering_faction +
                '}';
    }

}
