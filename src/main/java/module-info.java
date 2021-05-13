/**
 * Created by Elec332 on 30/04/2021
 */
module nl.elec332.planetside2api {

    requires com.google.gson;

    opens nl.elec332.planetside2.impl.objects to com.google.gson;
    opens nl.elec332.planetside2.impl.objects.misc to com.google.gson;
    opens nl.elec332.planetside2.impl.streaming.event to com.google.gson;
    opens nl.elec332.planetside2.impl.streaming.event.base to com.google.gson;

    exports nl.elec332.planetside2.api;
    exports nl.elec332.planetside2.api.objects;
    exports nl.elec332.planetside2.api.objects.misc;
    exports nl.elec332.planetside2.api.objects.player;
    exports nl.elec332.planetside2.api.objects.player.request;
    exports nl.elec332.planetside2.api.objects.registry;
    exports nl.elec332.planetside2.api.objects.weapons;
    exports nl.elec332.planetside2.api.objects.world;
    exports nl.elec332.planetside2.api.streaming;
    exports nl.elec332.planetside2.api.streaming.event;
    exports nl.elec332.planetside2.api.streaming.event.base;
    exports nl.elec332.planetside2.api.streaming.request;

    exports nl.elec332.planetside2.util;

    provides nl.elec332.planetside2.api.IPS2APIAccessor with nl.elec332.planetside2.impl.PS2APIAccessor;

}