package main.kitchen;

public enum Dish {
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);


    Dish(int duration) {
        this.duration = duration;
    }

    private int duration;

    public static String allDishesToString() {
        Dish[] values = values();
        String[] dishes = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            dishes[i] = values[i].toString();
        }
        return String.join(", ", dishes);
    }

    public int getDuration() {
        return duration;
    }

}
