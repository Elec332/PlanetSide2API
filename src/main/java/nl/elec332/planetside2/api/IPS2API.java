package nl.elec332.planetside2.api;

import nl.elec332.planetside2.api.player.*;
import nl.elec332.planetside2.api.registry.IPS2ItemRegistry;
import nl.elec332.planetside2.api.registry.IPS2Object;
import nl.elec332.planetside2.api.registry.IPS2ObjectManager;
import nl.elec332.planetside2.api.registry.IPS2ObjectRegistry;
import nl.elec332.planetside2.api.weapons.ICertificationLine;
import nl.elec332.planetside2.api.weapons.IItemCategory;
import nl.elec332.planetside2.api.weapons.IItemType;
import nl.elec332.planetside2.api.weapons.IVehicle;
import nl.elec332.planetside2.api.world.*;

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

    IPS2ObjectRegistry<? extends IBase> getBases();

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

}
