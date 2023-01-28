package main.kitchen;

import main.ConsoleHelper;
import main.statistic.StatisticManager;
import main.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;

public class Cook extends Observable {
    final String name;
    volatile boolean busy;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public synchronized void startCookingOrder(Order order) {
        busy = true;
        ConsoleHelper.writeMessage(String.format("Start cooking - %s cooking time %dmin",order,order.getTotalCookingTime()) );
        try {
            Thread.sleep(order.getTotalCookingTime() * 10L);
        } catch (InterruptedException e){
            ConsoleHelper.writeMessage(e.getMessage());
        }
        setChanged();
        notifyObservers(order);
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(),
                name,
                order.getTotalCookingTime() * 60,
                order.getDishes()));
        busy = false;
    }

    public boolean isBusy() {
        return busy;
    }
}
