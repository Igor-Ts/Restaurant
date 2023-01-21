package main;

import main.statistic.StatisticManager;

public class DirectorTablet {
    StatisticManager statisticManager = StatisticManager.getInstance();
    public void printAdvertisementProfit(){
        statisticManager.profitResult();
    }
    public void printCookWorkLoading(){
        statisticManager.cookWorkTimeResult();
    }
    public void printActiveVideoSet(){

    }
    public void printArchivedVideoSet(){

    }
}
