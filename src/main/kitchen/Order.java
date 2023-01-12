package main.kitchen;

import main.ConsoleHelper;
import main.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {


    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public int getTotalCookingTime() {
        int cookingDuration = 0;
        for (Dish duration:dishes) {
            cookingDuration += duration.getDuration();
        }
        return cookingDuration;
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    @Override
    public String toString() {
       return dishes.isEmpty() ?"" : String.format("Your order: %s of %s",dishes,tablet);
    }
}
