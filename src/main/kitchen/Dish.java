package main.kitchen;

public enum Dish {
    Fish,
    Steak,
    Soup,
    Juice,
    Water;

    public static String allDishesToString() {
        Dish[] values = values();
        String[] dishes = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            dishes[i] = values[i].toString();
        }
        return String.join(" ,", dishes);
    }

}
