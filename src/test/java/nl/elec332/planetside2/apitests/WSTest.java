package nl.elec332.planetside2.apitests;

//import com.neovisionaries.ws.client.*;

import nl.elec332.planetside2.ps2api.api.IPS2APIAccessor;
import nl.elec332.planetside2.ps2api.api.objects.IPS2API;
import nl.elec332.planetside2.ps2api.api.objects.player.IOutfit;
import nl.elec332.planetside2.ps2api.api.streaming.IStreamingService;
import nl.elec332.planetside2.ps2api.api.streaming.request.IEventServiceFactory;
import nl.elec332.planetside2.ps2api.util.NetworkUtil;

import java.io.IOException;

/**
 * Created by Elec332 on 01/05/2021
 */
public class WSTest {

    public static void main(String[] args) throws IOException {//, WebSocketException {
//        WebSocketFactory factory = new WebSocketFactory().setConnectionTimeout(5000);
//        WebSocket ws = factory.createSocket("wss://push.planetside2.com/streaming?environment=ps2&service-id=" + args[0]);
//        ws.addListener(new WebSocketAdapter() {
//            @Override
//            public void onTextMessage(WebSocket websocket, String text) throws Exception {
//                System.out.println(text);
//            }
//        });
//        ws.connect();
//        ws.sendText("{\"service\":\"event\",\"action\":\"subscribe\",\"worlds\":[\"1\"],\"eventNames\":[\"PlayerLogin\"]}");
//        String[] test = {"a", "d", "xfg", "sdhsdh"};
//        System.out.println(Arrays.toString(test));


        IPS2APIAccessor accessor = NetworkUtil.getAPIAccessor();
        accessor.setServiceId(args[0]);
        IStreamingService streamingService = accessor.createStreamingService();
        IEventServiceFactory eventServiceFactory = accessor.getEventServiceFactory();
        ;
        IPS2API api = accessor.getAPI();
        streamingService.addListener(eventServiceFactory.getFacilityControlType(), e -> {
            if (e.getContinent() == null) {
                return;
            }
            IOutfit outfit = e.getOutfit();
            if (outfit == null) {
                return;
            }
            System.out.println("---------------------");
            System.out.println(e.toString());
            System.out.println(e.getTimeStamp().getEpochSecond());
            if (e.getNewFaction() == e.getOldFaction()) {
                System.out.println("DEFEND: " + e.getFacility().getName() + "  " + outfit.getName());
            } else {
                System.out.println("CAPTURE: " + e.getFacility().getName() + "  " + outfit.getName());
            }
        });
        streamingService.addListener(eventServiceFactory.getMetaGameEventType(), e -> {
            System.out.println("----------------------META-----------------------------");
            System.out.println(e);
            System.out.println(e.getTimeStamp().getEpochSecond());
        });
        streamingService.addListener(eventServiceFactory.getContinentLockType(), e -> {
            System.out.println("---------------------LOCK-----------------------------");
            System.out.println(e);
            System.out.println(e.getTimeStamp().getEpochSecond());
        });
        streamingService.addListener(eventServiceFactory.getContinentUnlockType(), e -> {
            System.out.println("----------------------UNLOCK-----------------------------");
            System.out.println(e);
            System.out.println(e.getTimeStamp().getEpochSecond());
        });

        //streamingService.subscribeToEvent(eventServiceFactory.getFacilityControlType(), api.getServers().getByName("Cobalt"));
        streamingService.subscribeToEvent(eventServiceFactory.getMetaGameEventType(), eventServiceFactory.getContinentLockType(), eventServiceFactory.getContinentUnlockType());
    }

}
