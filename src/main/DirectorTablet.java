package main;

import main.ad.Advertisement;
import main.ad.StatisticAdvertisementManager;
import main.statistic.StatisticManager;

import java.util.List;

public class DirectorTablet {
    StatisticManager statisticManager = StatisticManager.getInstance();
    StatisticAdvertisementManager statisticAdvertisementManager = StatisticAdvertisementManager.getInstance();
    public void printAdvertisementProfit(){
        statisticManager.profitResult();
    }
    public void printCookWorkLoading(){
        statisticManager.cookWorkTimeResult();
    }
    public void printActiveVideoSet(){
        List<Advertisement> activeList = statisticAdvertisementManager.activeVideoSet();
        for (Advertisement active: activeList) {
            ConsoleHelper.writeMessage(String.format("%s - %d",active.getName(),active.getHits()));
        }
    }
    public void printArchivedVideoSet(){
        List<Advertisement> archivedList = statisticAdvertisementManager.archivedVideoSet();
        for (Advertisement archived: archivedList) {
            ConsoleHelper.writeMessage(archived.getName());
        }
    }
}
