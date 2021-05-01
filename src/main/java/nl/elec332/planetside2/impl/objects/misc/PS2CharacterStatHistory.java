package nl.elec332.planetside2.impl.objects.misc;

import nl.elec332.planetside2.api.player.request.ICharacterStatHistory;

import java.util.Map;

/**
 * Created by Elec332 on 26/04/2021
 */
public class PS2CharacterStatHistory implements ICharacterStatHistory {

    private String stat_name;
    private long all_time;
    private Map<String, Integer> day, week, month;

    @Override
    public String getStatName() {
        return this.stat_name;
    }

    @Override
    public long getAllTime() {
        return this.all_time;
    }

    @Override
    public int getDay(int day) {
        return getMap(this.day, "d", day);
    }

    @Override
    public int getWeek(int week) {
        return getMap(this.week, "w", week);
    }

    @Override
    public int getMonth(int month) {
        return getMap(this.month, "m", month);
    }

    private int getMap(Map<String, Integer> map, String per, int val) {
        if (val < 1) {
            throw new IllegalArgumentException();
        }
        return map.get(per + (val < 10 ? "0" + val : val));
    }

    @Override
    public String toString() {
        return "PS2StatHistory{" +
                "stat_name='" + stat_name + '\'' +
                ", all_time=" + all_time +
                ", day=" + day +
                ", week=" + week +
                ", month=" + month +
                '}';
    }

}
