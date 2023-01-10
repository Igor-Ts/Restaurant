package main;

import main.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return bufferedReader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List <Dish> dishList = new ArrayList<>();
        while (true) {
            writeMessage(Dish.allDishesToString());
            writeMessage("Please, choose dishes (if you want to leave from menu write exit)");
            String dish  = readString();
            dish = dish.substring(0,1).toUpperCase() + dish.substring(1);
            if (dish.equals("Exit")){
                break;
            }
            try {
                Dish dishToList = Dish.valueOf(dish);
                dishList.add(dishToList);
            } catch (IllegalArgumentException e) {
                writeMessage("These dishes isn't exist in menu");
            }
        }
        return dishList;
    }
}
