package fr.istic.coa.proxy;

import fr.istic.coa.observer.Observer;
import fr.istic.coa.strategy.DiffusionAlgorithm;
import fr.istic.coa.strategy.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author thomas
 * @author amona
 */
public class BaseSensor implements Sensor<Value> {
    
    private List<Observer> observers;
    private DiffusionAlgorithm algorithm;

    public BaseSensor() {
        observers = new ArrayList<>();
    }

    @Override
    public Value getValue() {
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
            o.updateAsync(this);
        }
    }
}
