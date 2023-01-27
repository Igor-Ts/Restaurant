package main;

import main.kitchen.Cook;
import main.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args) throws Exception {
        //Tablet tablet = new Tablet(5);
        Cook cook = new Cook("Sasha");
        Waiter waiter = new Waiter();
        DirectorTablet directorTablet = new DirectorTablet();

        cook.addObserver(waiter);
        List<Tablet> tablets = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            Tablet tablet = new Tablet(i);
            tablets.add(tablet);

        }
        //tablet.addObserver(cook);
        RandomOrderGeneratorTask randomOrderGeneratorTask = new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL);
        Thread t = new Thread(randomOrderGeneratorTask);
        t.start();

        Thread.sleep(3000);
        t.interrupt();
        //tablet.createOrder();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkLoading();


    }
}
