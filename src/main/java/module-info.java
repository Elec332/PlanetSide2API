/**
 * Created by Elec332 on 30/04/2021
 */
module nl.elec332.planetside2api {

    requires com.google.gson;

    opens nl.elec332.planetside2.impl.objects to com.google.gson;
    opens nl.elec332.planetside2.impl.objects.misc to com.google.gson;

    exports nl.elec332.planetside2.api;
    exports nl.elec332.planetside2.api.misc;
    exports nl.elec332.planetside2.api.player;
    exports nl.elec332.planetside2.api.player.request;
    exports nl.elec332.planetside2.api.registry;
    exports nl.elec332.planetside2.api.weapons;
    exports nl.elec332.planetside2.api.world;

    exports nl.elec332.planetside2.util;

    provides nl.elec332.planetside2.api.IPS2APIAccessor with nl.elec332.planetside2.impl.PS2APIAccessor;

}