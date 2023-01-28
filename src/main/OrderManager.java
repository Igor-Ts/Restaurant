package main;

import main.kitchen.Cook;
import main.kitchen.Order;
import main.statistic.StatisticManager;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderManager implements Observer {
    LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public OrderManager() {
        Thread thread = new Thread(() -> {

            while (!Thread.currentThread().isInterrupted()){
                outer:
                while (orderQueue.peek() != null){
                    for (Cook getCook:StatisticManager.getInstance().getCooks()) {
                        if (!getCook.isBusy()){
                            Order order = orderQueue.poll();
                            Thread threadCook = new Thread(() -> {
                                getCook.startCookingOrder(order);
                            });
                            threadCook.setDaemon(true);
                            threadCook.start();
                            continue outer;
                        }
                    }
                    break;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e){
                    ConsoleHelper.writeMessage(e.getMessage());
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
    public void update(Observable o, Object arg) {
        orderQueue.add((Order) arg);
    }
}
