package nl.elec332.planetside2.api;

/**
 * Created by Elec332 on 30/04/2021
 * <p>
 * Help, my eyes
 */
public interface IPS2APIAccessor {

    void setServiceId(String sid);

    ICensusAPI getCensusAPI();

    IPS2API getAPI();

    void stopAPI();

}
