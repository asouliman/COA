package fr.istic.coa.strategy;

import fr.istic.coa.proxy.Sensor;

/**
 * @author thomas
 * @author amona
 */
public class AtomicDiffusion implements DiffusionAlgorithm {
    
    private Sensor sensor;
    
    public AtomicDiffusion(Sensor sensor) {
        this.sensor = sensor;
    }
    
    @Override
    public void configure() {

    }

    @Override
    public void execute() {
        sensor.notifyObservers();
    }
}
