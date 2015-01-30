package fr.istic.coa.strategy;

import fr.istic.coa.proxy.Sensor;

/**
 * @author thomas
 * @author amona
 */
public interface DiffusionAlgorithm {
    public void configure(Sensor sensor);
    public void execute();
    public int getValue();
}

