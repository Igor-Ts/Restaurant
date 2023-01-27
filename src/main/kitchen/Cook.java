package main.kitchen;

import main.ConsoleHelper;
import main.statistic.StatisticManager;
import main.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;

public class Cook extends Observable {
    final String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void startCookingOrder(Order order) {
        ConsoleHelper.writeMessage(String.format("Start cooking - %s cooking time %dmin",order,order.getTotalCookingTime()) );
        setChanged();
        notifyObservers(order);

        StatisticManager.getInstance().register(new CookedOrderEventDataRow(order.getTablet().toString(),
                name,
                order.getTotalCookingTime() * 60,
                order.getDishes()));
    }
}
