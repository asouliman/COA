package fr.istic.coa.proxy;

import fr.istic.coa.observer.Observer;
import fr.istic.coa.strategy.DiffusionAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author thomas
 * @author amona
 */
public class Sensor implements ISensor {
    private int value;
    private List<Observer> observers;
    private DiffusionAlgorithm algorithm;

    public Sensor() {
        observers = new ArrayList<>();
        value = 0;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void tick() {
        value++;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }
}
