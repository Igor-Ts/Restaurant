package main.statistic;

import main.ConsoleHelper;
import main.kitchen.Cook;
import main.statistic.event.CookedOrderEventDataRow;
import main.statistic.event.EventDataRow;
import main.statistic.event.EventType;
import main.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {
    private static StatisticManager managerInstance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();
    Set<Cook> cooks = new HashSet<>();
    SimpleDateFormat sDF = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
    public static StatisticManager getInstance() {
        return managerInstance;
    }

    public void register(EventDataRow data){
        statisticStorage.put(data);
    }

    public void register(Cook cook){
        cooks.add(cook);
    }

    private Map<String, Long> advertisementProfitPerDay() {
        Map<String, Long> advDayProfit = new HashMap<>();
        List<EventDataRow> videoSelect = statisticStorage.getEventsByType(EventType.SELECTED_VIDEOS);

        for (EventDataRow video: videoSelect) {
            VideoSelectedEventDataRow e = (VideoSelectedEventDataRow) video;
            String getDate = sDF.format(videoSelect.get(0).getDate());
            if (advDayProfit.containsKey(getDate)){
                advDayProfit.put(getDate, advDayProfit.get(getDate) + e.getAmount());
            } else {
                advDayProfit.put(getDate,e.getAmount());
            }
        }
        return advDayProfit;
    }

    public void profitResult() {
        double totalForAll = 0;
        NavigableMap<String,Long> advDayProfit = new TreeMap<>(advertisementProfitPerDay()).descendingMap();
        for (Map.Entry<String,Long> show: advDayProfit.entrySet()) {
            String date = show.getKey();
            Double profit = show.getValue() / 100.0;
            ConsoleHelper.writeMessage(String.format("%s - %.2f",date,profit));
            totalForAll += show.getValue() / 100.0;
        }
        ConsoleHelper.writeMessage(String.format("Total - %.2f",totalForAll));
    }

    private Map<String, TreeMap<String, Integer>> cookWorkPerDay() {
        Map<String, TreeMap<String, Integer>> cookDayWork = new HashMap<>();
        List<EventDataRow> cookedOrder = statisticStorage.getEventsByType(EventType.COOKED_ORDER);

        for (EventDataRow order: cookedOrder) {
            CookedOrderEventDataRow e = (CookedOrderEventDataRow) order;
            String date = sDF.format(e.getDate());
            TreeMap<String, Integer> dailyCookLoad = cookDayWork.computeIfAbsent(date, k -> new TreeMap<>());

            String cookName = e.getCookName();
            int orderTime = e.getTime() / 60;
            Integer dailyTime = dailyCookLoad.get(cookName);

            dailyCookLoad.put(cookName,
                    dailyTime == null ? orderTime : dailyTime + orderTime);
        }
        return cookDayWork;
    }

    public void cookWorkTimeResult() {
        Map<String, TreeMap<String, Integer>> cookResult = cookWorkPerDay();
        TreeMap<String, Integer> cookWorkTime = new TreeMap<>();

        for (Map.Entry<String, TreeMap<String, Integer>> e: cookResult.entrySet()) {
            String date = e.getKey();
            NavigableMap <String,Integer> deskMap = e.getValue().descendingMap();
            for (Map.Entry<String, Integer> cookTime: deskMap.entrySet()) {
                String cookName = cookTime.getKey();
                int cookTimeMin = cookTime.getValue();
                if (cookWorkTime.containsKey(cookName)){
                    cookWorkTime.put(cookName,(cookWorkTime.get(cookName) + cookTimeMin)) ;
                } else {
                    cookWorkTime.put(cookName, cookTimeMin);
                }
            }
            ConsoleHelper.writeMessage(date);
            for (Map.Entry<String, Integer> finalCookResult: cookWorkTime.entrySet()) {
                ConsoleHelper.writeMessage(String.format("%s - %d min",finalCookResult.getKey(),finalCookResult.getValue()));
            }
        }
        ConsoleHelper.writeMessage("");
    }

    private class StatisticStorage {
        Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        private StatisticStorage() {
            for (EventType eventType: EventType.values()) {
                storage.put(eventType, new ArrayList<>());
            }
        }

        private void put(EventDataRow data){
            storage.get(data.getType()).add(data);
        }

        private List<EventDataRow> getEventsByType(EventType eventType) {
            return storage.get(eventType);
        }
    }

}
