package nl.elec332.planetside2.ps2api.api.streaming.event;

import nl.elec332.planetside2.ps2api.api.objects.player.ILoadout;
import nl.elec332.planetside2.ps2api.api.streaming.event.base.IPlayerAttackStreamingEvent;

/**
 * Created by Elec332 on 01/05/2021
 */
public interface IDeathEvent extends IPlayerAttackStreamingEvent {

    int getAttackerFireMode();

    boolean isCritical();

    boolean isHeadshot();

    ILoadout getLoadout();

}
