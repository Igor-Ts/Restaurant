package main.statistic;

import main.statistic.event.EventDataRow;
import main.statistic.event.EventType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticManager {
    private static StatisticManager managerInstance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();
    public StatisticManager() {
        for (EventType eventType: EventType.values()) {
            statisticStorage.storage.put(eventType, new ArrayList<>());
        }
    }



    public static StatisticManager getInstance() {
        return managerInstance;
    }

    public void register(EventDataRow data){
        statisticStorage.put(data);
    }

    private class StatisticStorage {
        Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        private void put(EventDataRow data){
            storage.get(data.getType()).add(data);
        }
    }

}
