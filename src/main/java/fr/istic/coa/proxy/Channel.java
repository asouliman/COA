package fr.istic.coa.proxy;

import fr.istic.coa.observer.Observable;
import fr.istic.coa.observer.Observer;

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
public class Channel implements SensorObserver, Observable {

    private Sensor sensor;
    private ScheduledExecutorService executorService;
    private List<Observer> observers;
    
    public Channel(ScheduledExecutorService executorService, Sensor sensor) {
        this.observers = new ArrayList<>();
        this.executorService = executorService;
        this.sensor = sensor;
    }

    @Override
    public void update(Sensor subject) {
        notifyObservers();
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

    public Future<Integer> getValue() {
        Callable<Integer> c = () -> sensor.getValue();
        Random r = new Random();
        int delay = r.nextInt(100);
        return executorService.schedule(c, delay, TimeUnit.MILLISECONDS);
    }
}
