package nl.elec332.planetside2.impl;

import nl.elec332.planetside2.api.ICensusAPI;
import nl.elec332.planetside2.api.objects.registry.IPS2ItemRegistry;
import nl.elec332.planetside2.api.objects.registry.IPS2ObjectRegistry;
import nl.elec332.planetside2.api.objects.weapons.IItemType;
import nl.elec332.planetside2.api.objects.world.IFaction;
import nl.elec332.planetside2.impl.objects.PS2Item;
import nl.elec332.planetside2.impl.registry.StaticPS2ObjectRegistry;

import java.util.function.Supplier;

/**
 * Created by Elec332 on 27/04/2021
 */
public class PS2ItemRegistry extends StaticPS2ObjectRegistry<PS2Item> implements IPS2ItemRegistry<PS2Item> {

    public PS2ItemRegistry(ICensusAPI api, Supplier<IPS2ObjectRegistry<? extends IItemType>> typeGetter, Supplier<IPS2ObjectRegistry<? extends IFaction>> factionGetter) {
        super(api, PS2Item.class, "item");
        this.typeGetter = typeGetter;
        this.factionGetter = factionGetter;
    }

    private final Supplier<IPS2ObjectRegistry<? extends IItemType>> typeGetter;
    private final Supplier<IPS2ObjectRegistry<? extends IFaction>> factionGetter;

    @Override
    public void update() {
        super.update();
//        List<Long> done = new ArrayList<>();
//        IPS2ObjectRegistry<? extends IItemType> typeRegistry = typeGetter.get();
//        IItemType weapon = typeRegistry.getByName("Weapon");
//        IItemType attachment = typeRegistry.getByName("Attachment");
//        updateMap(map -> {
//            fetchItems(map, "item_type_id=" + weapon.getId());
//            done.add(weapon.getId());
//            fetchItems(map, "item_type_id=" + attachment.getId() + "&faction_id=0");
//            factionGetter.get().stream().forEach(f -> fetchItems(map, "item_type_id=" + attachment.getId() + "&faction_id=" + f.getId()));
//            done.add(attachment.getId());
//            String req = typeRegistry.stream()
//                    .filter(t -> {
//                        String s = t.getFullName().toLowerCase(Locale.ROOT);
//                        return !done.contains(t.getId()) && !s.contains("cosmetic") && !s.contains("customization");
//                    })
//                    .peek(t -> done.add(t.getId()))
//                    .map(t -> t.getId() + "")
//                    .collect(Collectors.joining("&item_type_id="));
//            fetchItems(map, "item_type_id=" + req);
//            typeRegistry.stream()
//                    .filter(t -> !done.contains(t.getId()))
//                    .forEach(t -> fetchItems(map, "item_type_id=" + t.getId()));
//        });
    }

}
