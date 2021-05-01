package nl.elec332.planetside2.api.player.request;

/**
 * Created by Elec332 on 26/04/2021
 */
public interface ICharacterStatHistory extends IStat {

    @Override
    String getStatName();

    long getAllTime();

    int getDay(int day);

    int getWeek(int week);

    int getMonth(int month);

}
