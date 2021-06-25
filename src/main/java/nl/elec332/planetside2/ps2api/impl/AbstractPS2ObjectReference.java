package nl.elec332.planetside2.ps2api.impl;

import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2Object;
import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2ObjectReference;

import java.util.Objects;

/**
 * Created by Elec332 on 24/04/2021
 */
public abstract class AbstractPS2ObjectReference<T extends IPS2Object> implements IPS2ObjectReference<T> {

    protected AbstractPS2ObjectReference(long id) {
        this.id = id;
    }

    private final long id;

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IPS2ObjectReference)) {
            return false;
        }
        IPS2ObjectReference<?> r = (IPS2ObjectReference<?>) o;
        return this.id == r.getId() && getType() == r.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getType());
    }

    @Override
    public String toString() {
        return Objects.toString(getObject());
    }

}
