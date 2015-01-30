package fr.istic.coa.proxy;

import fr.istic.coa.observer.Observer;
import fr.istic.coa.strategy.DiffusionAlgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author thomas
 * @author amona
 */
public class BaseSensor implements Sensor {
    private List<Observer> observers;
    private DiffusionAlgorithm algorithm;

    public BaseSensor() {
        observers = new ArrayList<>();
    }

    @Override
    public int getValue() {
        return algorithm.getValue();
    }

    @Override
    public void tick() {
        algorithm.execute();
    }

    @Override
    public void setAlgorithm(DiffusionAlgorithm algorithm) {
        this.algorithm = algorithm;
        algorithm.configure(this);
    }

    @Override
    public List<Observer> getObservers() {
        return this.observers;
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
