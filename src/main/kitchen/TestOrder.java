package main.kitchen;

import main.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TestOrder extends Order{
    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() throws IOException {
        dishes = new ArrayList<>();
        Dish[] dishArray = Dish.values();
        int howManyDishes = new Random().nextInt(dishArray.length) + 1;
        while (howManyDishes-- > 0) {
            int dishNumber = new Random().nextInt(dishArray.length);
            dishes.add(dishArray[dishNumber]);
        }
    }
}
