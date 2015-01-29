package fr.istic.coa.proxy;

import fr.istic.coa.observer.Observable;
import fr.istic.coa.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author thomas
 * @author amona
 */
public class Channel implements ISensorObserver, Observable {

    private Sensor sensor;
    private ScheduledExecutorService executorService;
    private List<Observer> observers;
    
    public Channel() {
        executorService = Executors.newSingleThreadScheduledExecutor();
        observers = new ArrayList<>();
    }
    
    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
    
    @Override
    public void update(ISensor subject) {
        notifyObservers();
    }
    
    public Future<Integer> getValue() {
        Callable<Integer> c = () -> sensor.getValue();
        Random r = new Random();
        int delay = r.nextInt(1000);
        return executorService.schedule(c, delay, TimeUnit.MILLISECONDS);
    }
    
    public void shutdownExecutor() {
        executorService.shutdown();
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
