package nl.elec332.planetside2.ps2api.api.objects.player;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Created by Elec332 on 06/06/2021
 */
public interface IPlayerRequestList<R> extends ISlimPlayer {

    Collection<R> getResponse();

    R getFirstResponse();

    Stream<R> getResponseByName(String name);

    R getFirstResponseByName(String name);

}
