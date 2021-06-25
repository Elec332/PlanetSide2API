package nl.elec332.planetside2.ps2api.api.streaming.event;

import nl.elec332.planetside2.ps2api.api.objects.weapons.IItem;
import nl.elec332.planetside2.ps2api.api.streaming.event.base.IPlayerStreamingEvent;

/**
 * Created by Elec332 on 05/05/2021
 */
public interface IItemAddedEvent extends IPlayerStreamingEvent {

    String getContext();

    int getItemCount();

    IItem getItem();

}
