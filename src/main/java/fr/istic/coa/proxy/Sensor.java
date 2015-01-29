package fr.istic.coa.proxy;

import fr.istic.coa.observer.Observable;
import fr.istic.coa.observer.Observer;

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
     * Generates a new value.
     */
    public void tick();
}
