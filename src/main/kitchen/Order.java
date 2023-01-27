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
        initDishes();
    }

    public int getTotalCookingTime() {
        int cookingDuration = 0;
        for (Dish duration:dishes) {
            cookingDuration += duration.getDuration();
        }
        return cookingDuration;
    }

    protected void initDishes() throws IOException {
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public Tablet getTablet() {
        return tablet;
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    @Override
    public String toString() {
       return dishes.isEmpty() ?"" : String.format("Your order: %s of %s",dishes,tablet);
    }
}
