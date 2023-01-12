package main;

import main.kitchen.Cook;
import main.kitchen.Waiter;

public class Restaurant {

    public static void main(String[] args) throws Exception {
        Tablet tablet = new Tablet(5);
        Cook cook = new Cook("Sasha");
        Waiter waiter = new Waiter();
        tablet.addObserver(cook);
        cook.addObserver(waiter);
        tablet.createOrder();

    }
}
