package fr.istic.coa.proxy;

import fr.istic.coa.observer.Observable;
import fr.istic.coa.observer.Observer;
import fr.istic.coa.strategy.DiffusionAlgorithm;
import fr.istic.coa.strategy.Value;

import java.util.List;

/**
 * @author thomas
 * @author amona
 */
public interface Sensor<T> extends Observable {
    
    /**
     * Gets the value of the sensor.
     * @return the value of the sensor.
     */
    public T getValue();

    /**
     * Dispatch the value to the channels.
     */
    public void tick();

    /**
     * Sets the algorithm.
     * @param algorithm
     */
    void setAlgorithm(DiffusionAlgorithm algorithm);

    /**
     * Gets the sensor's observers.
     * @return
     */
    List<Observer> getObservers();
}
