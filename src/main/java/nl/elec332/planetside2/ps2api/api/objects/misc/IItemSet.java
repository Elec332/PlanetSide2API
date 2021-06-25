package nl.elec332.planetside2.ps2api.api.objects.misc;

import nl.elec332.planetside2.ps2api.api.objects.registry.IPS2ObjectReference;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Elec332 on 30/04/2021
 */
public interface IItemSet {

    Set<Integer> getItems();

    default IItemSet and(IItemSet... others) {
        return () -> {
            Set<Integer> ret = new HashSet<>(getItems());
            for (IItemSet itemSet : others) {
                ret.addAll(itemSet.getItems());
            }
            return ret;
        };
    }

    default IItemSet without(IItemSet... others) {
        return () -> {
            Set<Integer> ret = new HashSet<>(getItems());
            for (IItemSet itemSet : others) {
                ret.removeAll(itemSet.getItems());
            }
            return ret;
        };
    }

    default IItemSet matching(IItemSet... others) {
        return () -> {
            Set<Integer> ret = new HashSet<>(getItems());
            for (IItemSet itemSet : others) {
                ret.retainAll(itemSet.getItems());
            }
            return ret;
        };
    }

    static IItemSet wrap(int... itemIds) {
        final Set<Integer> values = Collections.unmodifiableSet(Arrays.stream(itemIds).boxed().collect(Collectors.toSet()));
        return () -> values;
    }

    static IItemSet wrap(Collection<Integer> itemIds) {
        final Set<Integer> values = Collections.unmodifiableSet(new HashSet<>(itemIds));
        return () -> values;
    }

    static IItemSet getDeferred(IPS2ObjectReference<? extends IItemSet> reference) {
        return () -> reference.getObject().getItems();
    }

}
