package nl.elec332.planetside2.impl.registry;

import nl.elec332.planetside2.api.objects.registry.IPS2Object;
import nl.elec332.planetside2.api.objects.registry.IPS2ObjectManager;
import nl.elec332.planetside2.impl.AbstractPS2ObjectReference;

/**
 * Created by Elec332 on 25/04/2021
 */
public abstract class AbstractPS2ObjectManager<T extends IPS2Object> implements IPS2ObjectManager<T> {

    public AbstractPS2ObjectManager(Class<T> type) {
        this.type = type;
    }

    private final Class<T> type;

    @Override
    public final Class<T> getType() {
        return this.type;
    }

    protected class DynamicRef extends AbstractRef {

        protected DynamicRef(long id) {
            super(id);
        }

        @Override
        public T getObject() {
            return getCached(getId());
        }

    }

    protected class CachedRef extends AbstractRef {

        protected CachedRef(long id) {
            super(id);
        }

        private T ref;

        protected void clear() {
            this.ref = null;
        }

        @Override
        public T getObject() {
            if (ref == null) {
                ref = getCached(getId());
            }
            return ref;
        }

    }

    protected abstract class AbstractRef extends AbstractPS2ObjectReference<T> {

        protected AbstractRef(long id) {
            super(id);
        }

        @Override
        public Class<T> getType() {
            return AbstractPS2ObjectManager.this.getType();
        }

    }

}
