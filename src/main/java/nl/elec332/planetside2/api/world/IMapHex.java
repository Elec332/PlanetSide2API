package nl.elec332.planetside2.api.world;

/**
 * Created by Elec332 on 24/04/2021
 */
public interface IMapHex {

    int getX();

    int getY();

    IHexType getHexType();

}
