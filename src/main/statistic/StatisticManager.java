package main.statistic;

import main.statistic.event.EventDataRow;

public class StatisticManager {

    private static StatisticManager managerInstance = new StatisticManager();

    public static StatisticManager getInstance() {
        return managerInstance;
    }

    public void register(EventDataRow data){

    }
}
