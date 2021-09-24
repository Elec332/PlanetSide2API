package nl.elec332.planetside2.ps2api.impl.streaming.request;

import nl.elec332.planetside2.ps2api.api.streaming.event.*;
import nl.elec332.planetside2.ps2api.api.streaming.event.base.IPlayerStreamingEvent;
import nl.elec332.planetside2.ps2api.api.streaming.event.base.IStreamingEvent;
import nl.elec332.planetside2.ps2api.api.streaming.request.IEventServiceFactory;
import nl.elec332.planetside2.ps2api.api.streaming.request.IStreamingEventType;
import nl.elec332.planetside2.ps2api.impl.streaming.event.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Elec332 on 05/05/2021
 */
public enum EventServiceFactory implements IEventServiceFactory {

    INSTANCE;

    private static final IStreamingEventType<IAchievementEarnedEvent> ACHIEVEMENT = new StreamingEventType<>(IAchievementEarnedEvent.class, "AchievementEarned");
    private static final IStreamingEventType<IBattleRankUpEvent> BR_UP = new StreamingEventType<>(IBattleRankUpEvent.class, "BattleRankUp");
    private static final IStreamingEventType<IContinentStateEvent> CONTINENT_LOCK = new StreamingEventType<>(IContinentStateEvent.class, "ContinentLock", e -> {
        if (e.getContinent() == null) {
            return false;
        }
        if (e.getTriggeringFaction() == null) {
            System.out.print("Null faction: ");
            System.out.println(e);
            return false;
        }
        return true;
    });
    private static final IStreamingEventType<IContinentStateEvent> CONTINENT_UNLOCK = new StreamingEventType<>(IContinentStateEvent.class, "ContinentUnlock");
    private static final IStreamingEventType<IDeathEvent> DEATH = new StreamingEventType<>(IDeathEvent.class, "Death");
    private static final IStreamingEventType<IFacilityControlEvent> FACILITY_CONTROL = new StreamingEventType<>(IFacilityControlEvent.class, "FacilityControl");
    private static final IStreamingEventType<IGainExperienceEvent> GAIN_XP = new StreamingEventType<>(IGainExperienceEvent.class, "GainExperience");
    private static final IStreamingEventType<IItemAddedEvent> ITEM_ADDED = new StreamingEventType<>(IItemAddedEvent.class, "ItemAdded");
    private static final IStreamingEventType<IMetaGameEventEvent> METAGAME = new StreamingEventType<>(IMetaGameEventEvent.class, "MetagameEvent");
    private static final IStreamingEventType<IPlayerFacilityEvent> PLAYER_FACILITY_CAPTURE = new StreamingEventType<>(IPlayerFacilityEvent.class, "PlayerFacilityCapture");
    private static final IStreamingEventType<IPlayerFacilityEvent> PLAYER_FACILITY_DEFEND = new StreamingEventType<>(IPlayerFacilityEvent.class, "PlayerFacilityDefend");
    private static final IStreamingEventType<IPlayerStreamingEvent> PLAYER_LOGIN = new StreamingEventType<>(IPlayerStreamingEvent.class, "PlayerLogin");
    private static final IStreamingEventType<IPlayerStreamingEvent> PLAYER_LOGOUT = new StreamingEventType<>(IPlayerStreamingEvent.class, "PlayerLogout");
    private static final IStreamingEventType<ISkillAddedEvent> SKILL_ADDED = new StreamingEventType<>(ISkillAddedEvent.class, "SkillAdded");
    private static final IStreamingEventType<IVehicleDestroyEvent> VEHICLE_DESTROY = new StreamingEventType<>(IVehicleDestroyEvent.class, "VehicleDestroy");

    private static final Map<Class<?>, Class<?>> EVENT_MAPPER = new HashMap<>();
    private static final Map<String, IStreamingEventType<?>> EVENT_MAP = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <T extends IStreamingEvent> Class<? extends T> getImplementation(Class<T> type) {
        return (Class<? extends T>) EVENT_MAPPER.get(type);
    }

    @Override
    public IStreamingEventType<?> getByName(String name) {
        return EVENT_MAP.get(name);
    }

    @Override
    public IStreamingEventType<IAchievementEarnedEvent> getAchievementType() {
        return ACHIEVEMENT;
    }

    @Override
    public IStreamingEventType<IBattleRankUpEvent> getBattleRankUpType() {
        return BR_UP;
    }

    @Override
    public IStreamingEventType<IContinentStateEvent> getContinentLockType() {
        return CONTINENT_LOCK;
    }

    @Override
    public IStreamingEventType<IContinentStateEvent> getContinentUnlockType() {
        return CONTINENT_UNLOCK;
    }

    @Override
    public IStreamingEventType<IDeathEvent> getDeathType() {
        return DEATH;
    }

    @Override
    public IStreamingEventType<IFacilityControlEvent> getFacilityControlType() {
        return FACILITY_CONTROL;
    }

    @Override
    public IStreamingEventType<IGainExperienceEvent> getGainExperienceType() {
        return GAIN_XP;
    }

    @Override
    public IStreamingEventType<IGainExperienceEvent> getGainExperienceType(int xpType) {
        return new StreamingEventType<>(GAIN_XP, xpType);
    }

    @Override
    public IStreamingEventType<IItemAddedEvent> getItemAddedType() {
        return ITEM_ADDED;
    }

    @Override
    public IStreamingEventType<IMetaGameEventEvent> getMetaGameEventType() {
        return METAGAME;
    }

    @Override
    public IStreamingEventType<IPlayerFacilityEvent> getPlayerFacilityCaptureType() {
        return PLAYER_FACILITY_CAPTURE;
    }

    @Override
    public IStreamingEventType<IPlayerFacilityEvent> getPlayerFacilityDefendType() {
        return PLAYER_FACILITY_DEFEND;
    }

    @Override
    public IStreamingEventType<IPlayerStreamingEvent> getPlayerLoginType() {
        return PLAYER_LOGIN;
    }

    @Override
    public IStreamingEventType<IPlayerStreamingEvent> getPlayerLogoutType() {
        return PLAYER_LOGOUT;
    }

    @Override
    public IStreamingEventType<ISkillAddedEvent> getSkillAddedType() {
        return SKILL_ADDED;
    }

    @Override
    public IStreamingEventType<IVehicleDestroyEvent> getVehicleDestroyType() {
        return VEHICLE_DESTROY;
    }

    static {
        EVENT_MAPPER.put(IAchievementEarnedEvent.class, AchievementEarnedEvent.class);
        EVENT_MAPPER.put(IBattleRankUpEvent.class, BattleRankUpEvent.class);
        EVENT_MAPPER.put(IContinentStateEvent.class, ContinentStateEvent.class);
        EVENT_MAPPER.put(IDeathEvent.class, DeathEvent.class);
        EVENT_MAPPER.put(IFacilityControlEvent.class, FacilityControlEvent.class);
        EVENT_MAPPER.put(IGainExperienceEvent.class, GainExperienceEvent.class);
        EVENT_MAPPER.put(IMetaGameEventEvent.class, MetaGameEventEvent.class);
        EVENT_MAPPER.put(IPlayerFacilityEvent.class, PlayerFacilityEvent.class);
        EVENT_MAPPER.put(IPlayerStreamingEvent.class, PlayerOnlineEvent.class);
        EVENT_MAPPER.put(ISkillAddedEvent.class, SkillAddedEvent.class);
        EVENT_MAPPER.put(IVehicleDestroyEvent.class, VehicleDestroyEvent.class);

        try {
            for (Field f : EventServiceFactory.class.getDeclaredFields()) {
                if (IStreamingEventType.class.isAssignableFrom(f.getType())) {
                    IStreamingEventType<?> r = (IStreamingEventType<?>) f.get(null);
                    EVENT_MAP.put(r.getEventName(), r);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to gather registries.", e);
        }
    }

}
