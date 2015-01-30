package fr.istic.coa.proxy;

import fr.istic.coa.observer.Observable;
import fr.istic.coa.observer.Observer;
import fr.istic.coa.strategy.DiffusionAlgorithm;

import java.util.List;

/**
 * @author thomas
 * @author amona
 */
public interface Sensor extends Observable<Observer> {
    /**
     * Gets the value of the sensor.
     * @return the value of the sensor.
     */
    public int getValue();

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
