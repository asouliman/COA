package fr.istic.coa.strategy;

import fr.istic.coa.proxy.ISensor;

/**
 * @author thomas
 * @author amona
 */
public class AtomicDiffusion implements DiffusionAlgorithm {
    
    private ISensor sensor;
    
    public AtomicDiffusion(ISensor sensor) {
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
