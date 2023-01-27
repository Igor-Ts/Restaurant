package main;

import main.ad.AdvertisementManager;
import main.ad.NoVideoAvailableException;
import main.kitchen.Order;
import main.kitchen.TestOrder;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet extends Observable {
    final int number;
    static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder() {
        Order order = null;
            order = createOrderbyOrderType(order,false);
        return order;
    }

    public void createTestOrder() {
        TestOrder order = null;
        createOrderbyOrderType(order, true);
    }

    private Order createOrderbyOrderType(Order order, boolean isAuto) {
        try {
            order = isAuto? new TestOrder(this): new Order(this);
            if (!order.isEmpty()){
                ConsoleHelper.writeMessage(order.toString());
                setChanged();
                notifyObservers(order);
                AdvertisementManager advertisementManager = new AdvertisementManager(order.getTotalCookingTime()*60);
                advertisementManager.processVideos();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        } catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the order " + order);
        }
        return order;
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
