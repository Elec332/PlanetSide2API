package nl.elec332.planetside2.ps2api.impl;

import nl.elec332.planetside2.ps2api.api.ICensusAPI;
import nl.elec332.planetside2.ps2api.api.objects.IPS2API;
import nl.elec332.planetside2.ps2api.api.objects.IPlayerRequestHandler;
import nl.elec332.planetside2.ps2api.api.objects.misc.IMetaGameEvent;
import nl.elec332.planetside2.ps2api.api.objects.misc.IMetaGameEventState;
import nl.elec332.planetside2.ps2api.api.objects.player.*;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2ItemRegistry;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2Object;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2ObjectManager;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2ObjectRegistry;
import nl.elec332.planetside2.ps2api.api.objects.weapons.*;
import nl.elec332.planetside2.ps2api.api.objects.world.*;
import nl.elec332.planetside2.ps2api.impl.objects.*;
import nl.elec332.planetside2.ps2api.impl.registry.*;
import nl.elec332.planetside2.ps2api.util.Constants;
import nl.elec332.planetside2.ps2api.util.census.CensusRequestBuilder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Elec332 on 23/04/2021
 */
class PS2APIImpl implements IPS2API {

    PS2APIImpl(ICensusAPI api) {
        this.threads = new ThreadGroup("PS2API_Group");
        this.managers = new ArrayList<>();
        this.registries = new ArrayList<>();
        this.servers = new StaticPS2ObjectRegistry<>(api, PS2Server.class, "world");
        this.continents = new StaticPS2ObjectRegistry<>(api, PS2Continent.class, "zone");
        this.facilityTypes = new StaticPS2ObjectRegistry<>(api, PS2FacilityType.class, "facility_type");
        this.factions = new StaticPS2ObjectRegistry<>(api, PS2Faction.class, "faction");
        this.bases = new StaticPS2ObjectRegistry<>(api, PS2Facility.class, "map_region", "c:join=map_hex^list:1^inject_at:hexes^show:x'y'hex_type");
        this.hexTypes = new StaticUndocumentedObjectRegistry<>(PS2HexType.class, r -> {
            r.accept(new PS2HexType(0, "Unrestricted", false));
            r.accept(new PS2HexType(2, "Restricted_Faction", true));
        });
        this.outfits = new DynamicCachedObjectManager<>(api, "outfit", PS2Outfit.class,
//                "&c:join=" +
//                        "characters_world^inject_at:world^on:leader_character_id^to:character_id^show:world_id," +
//                        "character^inject_at:faction^on:leader_character_id^to:character_id^show:faction_id," +
//                        "outfit_rank^list:1^inject_at:rank_map^show:name'ordinal" +
//                "&c:tree=start:rank_map^field:ordinal");

                new CensusRequestBuilder()
                        .join("characters_world", j -> j.injectAt("world").on("leader_character_id").to("character_id").show("world_id"))
                        .join("character", j -> j.injectAt("faction").on("leader_character_id").to("character_id").show("faction_id"))
                        .join("outfit_rank", j -> j.asList().injectAt("rank_map").show("name", "ordinal"))
                        .tree("rank_map", "ordinal")
                        .join("outfit_member", j -> j.asList().injectAt("member_map").show("character_id")
                                .nested("character", j2 -> j2.show("name.first", "times.last_save", "character_id").injectAt("character")
                                        .nested("outfit_member", j3 -> j3.on("character_id").injectAt("outfit_info").show("member_since", "rank_ordinal", "outfit_id"))
                                )
                        )
                        .tree("member_map", "character_id")
                        .build()
                , "name_lower", "alias_lower").setCacheCheck(o -> o.getMembers() < Constants.API_SAFE_RETURN_SIZE);
        this.players = new DynamicUncachedObjectManager<>(api, "character", PS2Player.class,
                new CensusRequestBuilder()
                        .show()
                        .show("character_id", "name.first", "faction_id", "times.creation", "times.last_save", "times.last_login", "battle_rank.value", "prestige_level")
                        .join("outfit_member", j -> j.injectAt("outfit_member").show("outfit_id"))
                        .join("characters_world", j -> j.injectAt("world").show("world_id"))
                        .build()
                , "name.first_lower");
        this.requestHandler = new PlayerRequestHandler(api);
        this.classes = new StaticUndocumentedObjectRegistry<>(PS2Class.class, r -> {
            r.accept(new PS2Class(1, "Infiltrator"));
            r.accept(new PS2Class(3, "Light Assault"));
            r.accept(new PS2Class(4, "Combat Medic"));
            r.accept(new PS2Class(5, "Engineer"));
            r.accept(new PS2Class(6, "Heavy Assault"));
            r.accept(new PS2Class(7, "MAX"));
        });
        this.profiles = new StaticPS2ObjectRegistry<>(api, PS2Profile.class, "profile", "c:hide=name,description&c:join=profile_2^on:profile_id^show:description^inject_at:type2");
        this.vehicles = new StaticPS2ObjectRegistry<>(api, PS2Vehicle.class, "vehicle", "c:hide=description&c:lang=en&c:join=vehicle_faction^show:faction_id^inject_at:faction");
        this.xpTypes = new StaticPS2ObjectRegistry<>(api, PS2ExperienceType.class, "experience");
        this.itemTypes = new StaticPS2ObjectRegistry<>(api, PS2ItemType.class, "item_type", "c:join=item^list:1^show:item_id^inject_at:items_list");
        this.itemCategories = new StaticPS2ObjectRegistry<>(api, PS2ItemCategory.class, "item_category", "c:lang=en&c:join=item^list:1^show:item_id^inject_at:items_list");
        this.itemRegistry = new PS2ItemRegistry(api, () -> this.itemTypes, () -> this.factions);
        this.certificationLines = new StaticPS2ObjectRegistry<>(api, PS2CertificationLine.class, "skill_set", "c:lang=en");
        this.loadouts = new StaticPS2ObjectRegistry<>(api, PS2Loadout.class, "loadout", "c:hide=faction_id");
        this.achievements = new StaticPS2ObjectRegistry<>(api, PS2Achievement.class, "achievement", "c:lang=en&c:show=achievement_id,item_id,repeatable,name,description");
        this.weapons = new StaticPS2ObjectRegistry<>(api, PS2Weapon.class, "weapon", "c:join=item_to_weapon^show:item_id^inject_at:item(item^inject_at:item^show:name.en)");
        this.metaGameEvents = new StaticPS2ObjectRegistry<>(api, PS2MetaGameEvent.class, "metagame_event", "c:lang=en");
        this.metaGameStates = new StaticPS2ObjectRegistry<>(api, PS2MetaGameEventState.class, "metagame_event_state");
        init();
    }

    private static IPS2API instance;

    private final ThreadGroup threads;
    private final List<IPS2ObjectManager<?>> managers;
    private final List<IPS2ObjectRegistry<?>> registries;
    private final StaticPS2ObjectRegistry<PS2Faction> factions;
    private final StaticPS2ObjectRegistry<PS2Server> servers;
    private final StaticPS2ObjectRegistry<PS2Continent> continents;
    private final StaticPS2ObjectRegistry<PS2FacilityType> facilityTypes;
    private final StaticPS2ObjectRegistry<PS2Facility> bases;
    private final IPS2ObjectRegistry<PS2HexType> hexTypes;
    private final DynamicCachedObjectManager<PS2Outfit> outfits;
    private final DynamicUncachedObjectManager<PS2Player> players;
    private final PlayerRequestHandler requestHandler;
    private final IPS2ObjectRegistry<PS2Class> classes;
    private final StaticPS2ObjectRegistry<PS2Profile> profiles;
    private final StaticPS2ObjectRegistry<PS2Vehicle> vehicles;
    private final StaticPS2ObjectRegistry<PS2ExperienceType> xpTypes;
    private final StaticPS2ObjectRegistry<PS2ItemType> itemTypes;
    private final StaticPS2ObjectRegistry<PS2ItemCategory> itemCategories;
    private final PS2ItemRegistry itemRegistry;
    private final StaticPS2ObjectRegistry<PS2CertificationLine> certificationLines;
    private final StaticPS2ObjectRegistry<PS2Loadout> loadouts;
    private final StaticPS2ObjectRegistry<PS2Achievement> achievements;
    private final StaticPS2ObjectRegistry<PS2Weapon> weapons;
    private final StaticPS2ObjectRegistry<PS2MetaGameEvent> metaGameEvents;
    private final StaticPS2ObjectRegistry<PS2MetaGameEventState> metaGameStates;

    private boolean valid;

    static <T extends IPS2Object> IPS2ObjectManager<T> getManagerEarly(Class<T> type) {
        return instance.getManager(type);
    }

    @SuppressWarnings("unused")
    public void startDaemon(Runnable runnable) {
        synchronized (PS2APIImpl.class) {
            if (valid) {
                Thread thread = new Thread(this.threads, runnable);
                thread.setDaemon(true);
                thread.start();
            } else {
                throw new IllegalStateException();
            }
        }
    }

    @SuppressWarnings({"rawtypes", "BusyWait"})
    private void init() {
        synchronized (PS2APIImpl.class) {
            if (instance != null) {
                throw new IllegalStateException();
            }
            instance = this;
            this.valid = true;
            try {
                for (Field f : getClass().getDeclaredFields()) {
                    if (IPS2ObjectManager.class.isAssignableFrom(f.getType())) {
                        IPS2ObjectManager r = (IPS2ObjectManager) f.get(this);
                        managers.add(r);
                        if (r instanceof IPS2ObjectRegistry) {
                            registries.add((IPS2ObjectRegistry<?>) r);
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to gather registries.", e);
            }
            updateData(0);
            new Thread(this.threads, () -> {
                int counter = 0;
                while (true) {
                    try {
                        Thread.sleep(60 * 1000);
                        updateData(counter);
                        counter++;
                        counter %= 60;
                    } catch (Exception e) {
                        System.out.println("UpdateFail");
                        e.printStackTrace();
                    }
                }
            }).start();
            System.out.println("Finished setting up API");
        }
    }

    @SuppressWarnings("deprecation")
    public void stop() {
        synchronized (PS2APIImpl.class) {
            if (!this.valid) {
                throw new IllegalStateException();
            }
            instance = null;
            this.threads.stop();
            this.valid = false;
        }
    }

    @Override
    public Collection<IPS2ObjectRegistry<?>> getAllRegistries() {
        return returnObject(this.registries);
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public <T extends IPS2Object> IPS2ObjectRegistry<T> getRegistry(Class<T> type) {
        return returnObject((IPS2ObjectRegistry) getAllRegistries().stream()
                .filter(e -> type.isAssignableFrom(e.getType()))
                .findFirst()
                .orElseThrow(NullPointerException::new));
    }

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public <T extends IPS2Object> IPS2ObjectManager<T> getManager(Class<T> type) {
        return returnObject((IPS2ObjectManager) this.managers.stream()
                .filter(e -> type.isAssignableFrom(e.getType()))
                .findFirst()
                .orElseThrow(NullPointerException::new));
    }

    @Override
    public IPS2ObjectRegistry<? extends IFaction> getFactions() {
        return returnObject(this.factions);
    }

    @Override
    public AbstractPS2ObjectRegistry<? extends IServer> getServers() {
        return returnObject(this.servers);
    }

    @Override
    public AbstractPS2ObjectRegistry<? extends IContinent> getContinents() {
        return returnObject(this.continents);
    }

    @Override
    public IPS2ObjectRegistry<? extends IFacilityType> getFacilityTypes() {
        return returnObject(this.facilityTypes);
    }

    @Override
    public IPS2ObjectRegistry<? extends IFacility> getFacilities() {
        return returnObject(this.bases);
    }

    @Override
    public IPS2ObjectRegistry<? extends IHexType> getHexTypes() {
        return returnObject(this.hexTypes);
    }

    @Override
    public IPS2ObjectManager<? extends IOutfit> getOutfitManager() {
        return returnObject(this.outfits);
    }

    @Override
    public IPS2ObjectManager<? extends IPlayer> getPlayerManager() {
        return returnObject(this.players);
    }

    @Override
    public IPlayerRequestHandler getPlayerRequestHandler() {
        return returnObject(this.requestHandler);
    }

    @Override
    public IPS2ObjectRegistry<? extends IPlayerClass> getPlayerClasses() {
        return returnObject(this.classes);
    }

    @Override
    public IPS2ObjectRegistry<? extends IPlayerProfile> getPlayerProfiles() {
        return returnObject(this.profiles);
    }

    @Override
    public IPS2ObjectRegistry<? extends IVehicle> getVehicles() {
        return returnObject(this.vehicles);
    }

    @Override
    public IPS2ObjectRegistry<? extends IExperienceType> getExperienceTypes() {
        return returnObject(this.xpTypes);
    }

    @Override
    public IPS2ObjectRegistry<? extends IItemType> getItemTypes() {
        return returnObject(this.itemTypes);
    }

    @Override
    public IPS2ObjectRegistry<? extends IItemCategory> getItemCategories() {
        return returnObject(this.itemCategories);
    }

    @Override
    public IPS2ItemRegistry<?> getItems() {
        return returnObject(this.itemRegistry);
    }

    @Override
    public IPS2ObjectRegistry<? extends ICertificationLine> getCertificationLines() {
        return returnObject(this.certificationLines);
    }

    @Override
    public IPS2ObjectRegistry<? extends ILoadout> getLoadouts() {
        return returnObject(this.loadouts);
    }

    @Override
    public IPS2ObjectRegistry<? extends IAchievement> getAchievements() {
        return returnObject(this.achievements);
    }

    @Override
    public IPS2ObjectRegistry<? extends IWeapon> getWeapons() {
        return returnObject(this.weapons);
    }

    @Override
    public IPS2ObjectRegistry<? extends IMetaGameEvent> getMetaGameEvents() {
        return returnObject(this.metaGameEvents);
    }

    @Override
    public IPS2ObjectRegistry<? extends IMetaGameEventState> getMetaGameEventState() {
        return returnObject(this.metaGameStates);
    }

    private void updateData(int minute) {
        if (minute == 0) {          //Every hour
            factions.update();
            continents.update();
            facilityTypes.update();
            bases.update();
            profiles.update();
            vehicles.update();
            xpTypes.update();
            itemTypes.update();
            itemCategories.update();
            itemRegistry.update();
            certificationLines.update();
            loadouts.update();
            achievements.update();
            weapons.update();
            metaGameEvents.update();
            metaGameStates.update();
        }
        if (minute % 3 == 0) {      //Every 3 mins
            servers.update();
        }
        if (minute % 10 == 0) {     //Every 10 mins
            outfits.update();
        }
    }

    private <T> T returnObject(T ret) {
        if (!valid) {
            throw new IllegalStateException();
        }
        return ret;
    }

}
