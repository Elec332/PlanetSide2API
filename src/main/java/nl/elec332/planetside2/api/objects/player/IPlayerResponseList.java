package nl.elec332.planetside2.api.objects.player;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by Elec332 on 27/04/2021
 */
public interface IPlayerResponseList<R extends ISlimPlayer> {

    List<R> getAsList();

    default <T extends Number> Stream<Map.Entry<String, T>> streamResponse(Function<R, T> mapper, T minimum, Comparator<T> comparator) {
        return streamResponse(mapper, comparator).filter(e -> e.getValue().doubleValue() > minimum.doubleValue());
    }

    default <T> Stream<Map.Entry<String, T>> streamResponse(Function<R, T> mapper, Comparator<T> comparator) {
        return streamResponse(mapper)
                .sorted(Map.Entry.comparingByValue(comparator));
    }

    default <C, T> Stream<Map.Entry<String, T>> streamMappedResponse(Function<R, Map.Entry<C, T>> mapper, Comparator<C> comparator) {
        return streamResponse(mapper)
                .sorted(Map.Entry.comparingByValue(Map.Entry.comparingByKey(comparator)))
                .map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), e.getValue().getValue()));
    }

    default <T> Stream<Map.Entry<String, T>> streamResponse(Function<R, T> mapper) {
        return getAsList().stream()
                .map(r -> (Map.Entry<String, T>) new AbstractMap.SimpleEntry<>(r.getName(), mapper.apply(r)))
                .filter(e -> e.getValue() != null);
    }

}
