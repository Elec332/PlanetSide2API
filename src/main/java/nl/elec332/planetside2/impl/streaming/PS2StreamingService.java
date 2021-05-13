package nl.elec332.planetside2.impl.streaming;

import com.google.gson.JsonObject;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import nl.elec332.planetside2.api.objects.world.IServer;
import nl.elec332.planetside2.api.streaming.IHeartBeatMessage;
import nl.elec332.planetside2.api.streaming.IStreamingService;
import nl.elec332.planetside2.api.streaming.event.base.IStreamingEvent;
import nl.elec332.planetside2.api.streaming.request.IStreamingEventType;
import nl.elec332.planetside2.impl.streaming.request.EventServiceRequest;
import nl.elec332.planetside2.util.NetworkUtil;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by Elec332 on 01/05/2021
 */
public class PS2StreamingService implements IStreamingService {

    public PS2StreamingService(String sid) throws IOException, WebSocketException {
        this.heartbeatListeners = new HashSet<>();
        this.eventListeners = new HashMap<>();

        WebSocketFactory factory = new WebSocketFactory().setConnectionTimeout(5000);
        this.webSocket = factory.createSocket("wss://push.planetside2.com/streaming?environment=ps2&service-id=" + sid);
        this.webSocket.addListener(new WebSocketAdapter() {

            @Override
            public void onTextMessage(WebSocket websocket, String text) {
                PS2StreamingService.this.onTextMessage(text);
            }

        });
        this.webSocket.connect();
    }

    private static final String CONNECT_CONFIRM = "{\"connected\":\"true\",\"service\":\"push\",\"type\":\"connectionStateChanged\"}";
    private static final String HELP_MESSAGE = "{\"send this for help\":{\"service\":\"event\",\"action\":\"help\"}}";

    private final WebSocket webSocket;
    private final Set<Consumer<IHeartBeatMessage>> heartbeatListeners;
    private boolean confirmed, helped;
    private Map<String, Set<Consumer<IStreamingEvent>>> eventListeners;

    private void onTextMessage(String txt) {
        if (!this.confirmed) {
            if (txt.equals(CONNECT_CONFIRM)) {
                confirmed = true;
                return;
            }
            throw new IllegalStateException();
        }
        if (!helped && txt.equals(HELP_MESSAGE)) {
            helped = true;
            return;
        }
        JsonObject object = NetworkUtil.GSON.fromJson(txt, JsonObject.class);
        String type = object.get("type").getAsString();
        if (type == null || type.equals("serviceStateChanged")) {
            System.out.println(txt);
            return;
        }
        if (type.equals("heartbeat")) {
            HeartBeatEvent h = new HeartBeatEvent(object.getAsJsonObject("online"));
            heartbeatListeners.forEach(l -> l.accept(h));
            return;
        }
        if (type.equals("serviceMessage")) {
            try {
                IStreamingEvent e = NetworkUtil.GSON.fromJson(object.get("payload"), IStreamingEvent.class);
                getListeners(e.getEventName()).forEach(c -> c.accept(e));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        throw new UnsupportedOperationException("Invalid type: " + type);
    }

    private Set<Consumer<IStreamingEvent>> getListeners(String name) {
        return this.eventListeners.computeIfAbsent(name, n -> new HashSet<>());
    }

    @Override
    public void clearSubscribe(IStreamingEventType<?>... type) {
        sendRequest(EventServiceRequest.createBaseRequest("clearSubscribe", null, null, Arrays.stream(type).map(IStreamingEventType::getSpecifiedEventName).collect(Collectors.toList())));
    }

    @Override
    public void subscribeToEvent(IStreamingEventType<?>... type) {
        sendRequest(EventServiceRequest.createBaseRequest("subscribe", null, null, Arrays.stream(type).map(IStreamingEventType::getSpecifiedEventName).collect(Collectors.toList())));
    }

    @Override
    public void subscribeToEvent(IStreamingEventType<?> type, IServer... servers) {
        sendRequest(EventServiceRequest.createBaseRequest("subscribe", null, Arrays.stream(servers).map(IServer::getId).collect(Collectors.toSet()), type.getSpecifiedEventName()));
    }

    @Override
    public void subscribeToEvent(IStreamingEventType<?> type, long... players) {
        sendRequest(EventServiceRequest.createBaseRequest("subscribe", Arrays.stream(players).boxed().collect(Collectors.toSet()), null, type.getSpecifiedEventName()));
    }

    private void sendRequest(EventServiceRequest request) {
        String req = "";
        req += "{";
        Map<String, String> r = request.toMap();
        req += r.entrySet().stream().map(e -> "\"" + e.getKey() + "\":" + e.getValue()).collect(Collectors.joining(","));
        req += "}";
        System.out.println("REQ: " + req);
        this.webSocket.sendText(req);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends IStreamingEvent> void addListener(IStreamingEventType<T> type, Consumer<T> listener) {
        getListeners(type.getEventName()).add((Consumer<IStreamingEvent>) listener);
    }

    @Override
    public <T extends IStreamingEvent> void removeListener(IStreamingEventType<T> type, Consumer<T> listener) {
        getListeners(type.getEventName()).remove(listener);
    }

    @Override
    public void removeListeners(IStreamingEventType<?> type) {
        getListeners(type.getEventName()).clear();
    }

    @Override
    public void addHeartBeatListener(Consumer<IHeartBeatMessage> listener) {
        this.heartbeatListeners.add(listener);
    }

    @Override
    public void clearSubscriptions() {
        this.webSocket.sendText("{\"action\": \"clearSubscribe\",\"all\": \"true\",\"service\": \"event\"}");
    }

    @Override
    public void stop() {
        this.webSocket.disconnect();
    }

}
