package main.kitchen;

import main.ConsoleHelper;
import main.statistic.StatisticManager;
import main.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {
    private String name;
    private volatile boolean busy;
    private LinkedBlockingQueue<Order> queue;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    private synchronized void startCookingOrder(Order order) {
        busy = true;
        ConsoleHelper.writeMessage(String.format("Start cooking - %s cooking time %dmin",order,order.getTotalCookingTime()) );
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(),
                name,
                order.getTotalCookingTime() * 60,
                order.getDishes()));
        try {
            Thread.sleep(order.getTotalCookingTime() * 10L);
        } catch (InterruptedException e){
            ConsoleHelper.writeMessage(e.getMessage());
        }
        setChanged();
        notifyObservers(order);
        busy = false;
    }

    private boolean isBusy() {
        return busy;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (!isBusy() && queue.peek() != null) {
                Order order = queue.poll();
                if (order != null) {
                    startCookingOrder(order);
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage(e.getMessage());
            }
        }
    }

}
