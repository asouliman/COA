package fr.istic.coa.proxy;

import fr.istic.coa.observer.Observer;
import fr.istic.coa.strategy.AtomicDiffusion;
import fr.istic.coa.strategy.DiffusionAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author thomas
 * @author amona
 */
public class BaseSensor implements Sensor {
    private int value;
    private List<Observer> observers;
    private DiffusionAlgorithm algorithm;

    public BaseSensor() {
        value = 0;
        observers = new ArrayList<>();
        algorithm = new AtomicDiffusion(this);
        algorithm.configure();
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void tick() {
        value++;
        algorithm.execute();
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(this);
        }
    }
}
