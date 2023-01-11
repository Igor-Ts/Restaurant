package main;

import main.kitchen.Cook;

public class Restaurant {

    public static void main(String[] args) throws Exception {
        Cook cook = new Cook("Sasha");
        Tablet tablet = new Tablet(5);
        tablet.addObserver(cook);
        tablet.createOrder();

    }
}
