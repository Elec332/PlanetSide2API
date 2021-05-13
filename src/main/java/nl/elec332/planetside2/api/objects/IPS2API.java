package nl.elec332.planetside2.api.objects;

import nl.elec332.planetside2.api.objects.misc.IMetaGameEvent;
import nl.elec332.planetside2.api.objects.misc.IMetaGameEventState;
import nl.elec332.planetside2.api.objects.player.*;
import nl.elec332.planetside2.api.objects.registry.IPS2ItemRegistry;
import nl.elec332.planetside2.api.objects.registry.IPS2Object;
import nl.elec332.planetside2.api.objects.registry.IPS2ObjectManager;
import nl.elec332.planetside2.api.objects.registry.IPS2ObjectRegistry;
import nl.elec332.planetside2.api.objects.weapons.*;
import nl.elec332.planetside2.api.objects.world.*;

import java.util.Collection;

/**
 * Created by Elec332 on 23/04/2021
 */
public interface IPS2API {

    Collection<IPS2ObjectRegistry<?>> getAllRegistries();

    <T extends IPS2Object> IPS2ObjectRegistry<T> getRegistry(Class<T> type);

    <T extends IPS2Object> IPS2ObjectManager<T> getManager(Class<T> type);

    IPS2ObjectRegistry<? extends IFaction> getFactions();

    IPS2ObjectRegistry<? extends IServer> getServers();

    IPS2ObjectRegistry<? extends IContinent> getContinents();

    IPS2ObjectRegistry<? extends IFacilityType> getFacilityTypes();

    IPS2ObjectRegistry<? extends IFacility> getFacilities();

    IPS2ObjectRegistry<? extends IHexType> getHexTypes();

    IPS2ObjectManager<? extends IOutfit> getOutfitManager();

    IPS2ObjectManager<? extends IPlayer> getPlayerManager();

    IPlayerRequestHandler getPlayerRequestHandler();

    IPS2ObjectRegistry<? extends IPlayerClass> getPlayerClasses();

    IPS2ObjectRegistry<? extends IPlayerProfile> getPlayerProfiles();

    IPS2ObjectRegistry<? extends IVehicle> getVehicles();

    IPS2ObjectRegistry<? extends IExperienceType> getExperienceTypes();

    IPS2ObjectRegistry<? extends IItemType> getItemTypes();

    IPS2ObjectRegistry<? extends IItemCategory> getItemCategories();

    IPS2ItemRegistry<?> getItems();

    IPS2ObjectRegistry<? extends ICertificationLine> getCertificationLines();

    /**
     * This type is stupid and shouldn't exist. Use {@link IPS2API#getPlayerProfiles()} if possible.
     */
    IPS2ObjectRegistry<? extends ILoadout> getLoadouts();

    IPS2ObjectRegistry<? extends IAchievement> getAchievements();

    IPS2ObjectRegistry<? extends IWeapon> getWeapons();

    IPS2ObjectRegistry<? extends IMetaGameEvent> getMetaGameEvents();

    IPS2ObjectRegistry<? extends IMetaGameEventState> getMetaGameEventState();

}
