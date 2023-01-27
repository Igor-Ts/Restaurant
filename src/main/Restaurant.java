package main;

import main.kitchen.Cook;
import main.kitchen.Waiter;
import main.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args) throws Exception {
        Cook cookSasha = new Cook("Sasha");
        Cook cookEgor = new Cook("Egor");
        StatisticManager statisticManager = StatisticManager.getInstance();
        statisticManager.register(cookSasha);
        statisticManager.register(cookEgor);
        Waiter waiter = new Waiter();
        DirectorTablet directorTablet = new DirectorTablet();
        cookEgor.addObserver(waiter);
        cookSasha.addObserver(waiter);
        List<Tablet> tablets = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            Tablet tablet = new Tablet(i);
            tablets.add(tablet);
            tablet.addObserver(cookSasha);
            tablet.addObserver(cookEgor);
        }
        RandomOrderGeneratorTask randomOrderGeneratorTask = new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL);
        Thread t = new Thread(randomOrderGeneratorTask);
        t.start();

        Thread.sleep(3000);
        t.interrupt();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkLoading();


    }
}
