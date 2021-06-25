package nl.elec332.planetside2.ps2api.api.objects.player;

/**
 * Created by Elec332 on 26/04/2021
 */
public interface IPlayerRequest<R> extends ISlimPlayer {

    R getResponse();

}
