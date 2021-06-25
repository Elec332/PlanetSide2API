package nl.elec332.planetside2.ps2api.api.streaming.request;

import nl.elec332.planetside2.ps2api.api.streaming.event.*;
import nl.elec332.planetside2.ps2api.api.streaming.event.base.IPlayerStreamingEvent;

/**
 * Created by Elec332 on 01/05/2021
 */
public interface IEventServiceFactory {

    IStreamingEventType<?> getByName(String name);

    IStreamingEventType<IAchievementEarnedEvent> getAchievementType();

    IStreamingEventType<IBattleRankUpEvent> getBattleRankUpType();

    IStreamingEventType<IContinentStateEvent> getContinentLockType();

    IStreamingEventType<IContinentStateEvent> getContinentUnlockType();

    IStreamingEventType<IDeathEvent> getDeathType();

    IStreamingEventType<IFacilityControlEvent> getFacilityControlType();

    IStreamingEventType<IGainExperienceEvent> getGainExperienceType();

    IStreamingEventType<IGainExperienceEvent> getGainExperienceType(int xpType);

    IStreamingEventType<IItemAddedEvent> getItemAddedType();

    IStreamingEventType<IMetaGameEventEvent> getMetaGameEventType();

    IStreamingEventType<IPlayerFacilityEvent> getPlayerFacilityCaptureType();

    IStreamingEventType<IPlayerFacilityEvent> getPlayerFacilityDefendType();

    IStreamingEventType<IPlayerStreamingEvent> getPlayerLoginType();

    IStreamingEventType<IPlayerStreamingEvent> getPlayerLogoutType();

    IStreamingEventType<ISkillAddedEvent> getSkillAddedType();

    IStreamingEventType<IVehicleDestroyEvent> getVehicleDestroyType();

}
