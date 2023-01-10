package main.kitchen;

import main.Tablet;

import java.util.List;

public class Order {


    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) {
        this.tablet = tablet;
    }
}
