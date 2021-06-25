package nl.elec332.planetside2.ps2api.impl.registry;

import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2Object;

import java.util.function.Consumer;

/**
 * Created by Elec332 on 24/04/2021
 */
public final class StaticUndocumentedObjectRegistry<T extends IPS2Object> extends AbstractPS2ObjectRegistry<T> {

    public StaticUndocumentedObjectRegistry(Class<T> type, Consumer<Consumer<T>> registry) {
        super(type);
        updateMap(m -> registry.accept(o -> m.put(o.getId(), o)));
    }

}
