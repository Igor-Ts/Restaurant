package main;

import main.kitchen.Cook;
import main.kitchen.Order;
import main.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
    public static void main(String[] args) throws Exception {
        Cook cookSasha = new Cook("Sasha");
        cookSasha.setQueue(orderQueue);
        Cook cookEgor = new Cook("Egor");
        cookEgor.setQueue(orderQueue);
        Cook cookMax = new Cook("Max");
        cookMax.setQueue(orderQueue);


        Waiter waiter = new Waiter();
        cookEgor.addObserver(waiter);
        cookSasha.addObserver(waiter);
        cookMax.addObserver(waiter);

        List<Tablet> tablets = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            Tablet tablet = new Tablet(i);
            tablets.add(tablet);
            tablet.setOrderQueue(orderQueue);
        }

        Thread threadCookSasha = new Thread(cookSasha);
        threadCookSasha.start();
        Thread threadCookEgor = new Thread(cookEgor);
        threadCookEgor.start();
        Thread threadCookMax = new Thread(cookMax);
        threadCookMax.start();

        RandomOrderGeneratorTask randomOrderGeneratorTask = new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL);
        Thread thread = new Thread(randomOrderGeneratorTask);
        thread.start();

        Thread.sleep(1000);
        thread.interrupt();
        threadCookEgor.interrupt();
        threadCookSasha.interrupt();
        threadCookMax.interrupt();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkLoading();
    }
}
