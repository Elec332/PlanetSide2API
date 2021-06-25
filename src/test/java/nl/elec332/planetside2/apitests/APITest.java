package nl.elec332.planetside2.apitests;

import nl.elec332.planetside2.ps2api.api.IPS2APIAccessor;
import nl.elec332.planetside2.ps2api.api.objects.IPS2API;
import nl.elec332.planetside2.ps2api.util.NetworkUtil;
import nl.elec332.planetside2.ps2api.util.PS2ItemSets;

/**
 * Created by Elec332 on 23/04/2021
 */
public class APITest {

    public static void main(String[] args) {
        System.out.println("Initializing-----------------------");
        IPS2APIAccessor accessor = NetworkUtil.getAPIAccessor();
        accessor.setServiceId(args[0]);
        IPS2API api = accessor.getAPI();
        api.getServers();
        System.out.println("Initialized------------------------");
        test(api);
        test2(api);
        System.out.println(PS2ItemSets.BASTION.getId());
        //System.out.println(accessor.getStreamEventPoller().getMetaGameEvents(Instant.now().minus(1, ChronoUnit.DAYS), 13));
        System.out.println("Stopping---------------------------");
        accessor.stopAPI();
        System.out.println("Stopped----------------------------");
    }

    private static void test(IPS2API api) {
        System.out.println("Factions---------------------------");
        api.getFactions().forEach((t, i) -> System.out.println(t));
        System.out.println("Servers----------------------------");
        api.getServers().forEach((t, i) -> System.out.println(t));
        System.out.println("Continents-------------------------");
        api.getContinents().forEach((t, i) -> System.out.println(t));
        System.out.println("FacilityTypes----------------------");
        api.getFacilityTypes().forEach((t, i) -> System.out.println(t));
        System.out.println("Bases------------------------------");
        api.getFacilities().forEach((t, i) -> System.out.println(t));
        System.out.println("HexTypes---------------------------");
        api.getHexTypes().forEach((t, i) -> System.out.println(t));
        System.out.println("PlayerClasses----------------------");
        api.getPlayerClasses().forEach((t, i) -> System.out.println(t));
        System.out.println("PlayerProfiles---------------------");
        api.getPlayerProfiles().forEach((t, i) -> System.out.println(t));
//        System.out.println("Vehicles---------------------------");
//        api.getVehicles().forEach((t, i) -> System.out.println(t));
//        System.out.println("XPTypes----------------------------");
//        api.getExperienceTypes().forEach((t, i) -> System.out.println(t));
//        System.out.println("ItemTypes--------------------------");
//        api.getItemTypes().forEach((t, i) -> System.out.println(t));
//        System.out.println("ItemCategories---------------------");
//        api.getItemCategories().forEach((t, i) -> System.out.println(t));
//        System.out.println("Items------------------------------");
//        api.getItems().forEach((t, i) -> System.out.println(t));
        System.out.println(api.getItems().stream().count());
//        System.out.println("CertificationLines-----------------");
//        api.getCertificationLines().forEach((t, i) -> System.out.println(t));
        System.out.println("Loadouts---------------------------");
        api.getLoadouts().forEach((t, i) -> System.out.println(t));
    }

    private static void test2(IPS2API api) {
        System.out.println("Outfit-----------------------------");
        System.out.println(api.getOutfitManager().get(37577844411345990L));
        System.out.println("Player-----------------------------");
        System.out.println(api.getPlayerManager().get(5428028648513432385L));
    }

}
