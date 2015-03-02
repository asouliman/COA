package fr.istic.coa.proxy;

import fr.istic.coa.observer.Observable;
import fr.istic.coa.observer.Observer;
import fr.istic.coa.strategy.DiffusionAlgorithm;
import fr.istic.coa.strategy.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author thomas
 * @author amona
 */
public class Channel implements Sensor<Future<Value>>, SensorObserver, Observable {

    private static final int DELAY = 1000;
    
    private Sensor<Value> sensor;
    private List<Observer> observers;
    private ScheduledExecutorService executorService;

    public Channel(ScheduledExecutorService executorService, Sensor<Value> sensor) {
        this.sensor = sensor;
        this.observers = new ArrayList<>();
        this.executorService = executorService;
    }

    @Override
    public void update(Sensor<Value> subject) {
        notifyObservers();
    }

    @Override
    public Future<Integer> updateAsync(Sensor<Value> subject) {
        return executorService.submit(() -> {
            notifyObservers();
            return 0;
        });
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

    @Override
    public Future<Value> getValue() {
        Callable<Value> c = sensor::getValue;
        Random r = new Random();
        int delay = r.nextInt(DELAY);
        return executorService.schedule(c, delay, TimeUnit.MILLISECONDS);
    }

    @Override
    public void tick() {
        sensor.tick();
    }

    @Override
    public void setAlgorithm(DiffusionAlgorithm algorithm) {
        sensor.setAlgorithm(algorithm);
    }

    @Override
    public List<Observer> getObservers() {
        return sensor.getObservers();
    }
}
