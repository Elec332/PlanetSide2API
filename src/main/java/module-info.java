/**
 * Created by Elec332 on 30/04/2021
 */
module nl.elec332.planetside2.api {

    requires transitive com.google.gson;

    opens nl.elec332.planetside2.ps2api.impl.objects to com.google.gson;
    opens nl.elec332.planetside2.ps2api.impl.objects.misc to com.google.gson;
    opens nl.elec332.planetside2.ps2api.impl.streaming.event to com.google.gson;
    opens nl.elec332.planetside2.ps2api.impl.streaming.event.base to com.google.gson;

    exports nl.elec332.planetside2.ps2api.api;
    exports nl.elec332.planetside2.ps2api.api.objects;
    exports nl.elec332.planetside2.ps2api.api.objects.misc;
    exports nl.elec332.planetside2.ps2api.api.objects.player;
    exports nl.elec332.planetside2.ps2api.api.objects.player.request;
    exports nl.elec332.planetside2.ps2api.api.objects.registry;
    exports nl.elec332.planetside2.ps2api.api.objects.weapons;
    exports nl.elec332.planetside2.ps2api.api.objects.world;
    exports nl.elec332.planetside2.ps2api.api.streaming;
    exports nl.elec332.planetside2.ps2api.api.streaming.event;
    exports nl.elec332.planetside2.ps2api.api.streaming.event.base;
    exports nl.elec332.planetside2.ps2api.api.streaming.request;

    exports nl.elec332.planetside2.ps2api.util;

    provides nl.elec332.planetside2.ps2api.api.IPS2APIAccessor with nl.elec332.planetside2.ps2api.impl.PS2APIAccessor;

}