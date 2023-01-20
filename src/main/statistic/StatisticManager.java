package main.statistic;

import main.kitchen.Cook;
import main.statistic.event.EventDataRow;
import main.statistic.event.EventType;

import java.util.*;

public class StatisticManager {
    private static StatisticManager managerInstance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();
    Set<Cook> cooks = new HashSet<>();
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

    public void register(Cook cook){
        cooks.add(cook);
    }

    private class StatisticStorage {
        Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        private void put(EventDataRow data){
            storage.get(data.getType()).add(data);
        }
    }

}
