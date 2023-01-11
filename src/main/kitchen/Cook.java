package main.kitchen;

import main.ConsoleHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Cook extends Observable implements Observer {
    final String name;
    private final List<Observer> observers = new ArrayList<>();

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cook{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public void update(Observable o, Object arg) {
        ConsoleHelper.writeMessage("Start cooking - " + arg);
        setChanged();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }
}
