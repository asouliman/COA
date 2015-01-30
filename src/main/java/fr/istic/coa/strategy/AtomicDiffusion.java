package fr.istic.coa.strategy;

import fr.istic.coa.proxy.Sensor;

/**
 * @author thomas
 * @author amona
 */
public class AtomicDiffusion implements DiffusionAlgorithm {
    private int value;
    private Sensor sensor;
    private int notifiedObservers;
    
    public AtomicDiffusion() {
        value = 0;
        notifiedObservers = 0;
    }

    @Override
    public void configure(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public void execute() {
        if (notifiedObservers == 0) {
            value++;
            sensor.notifyObservers();
        }
    }

    @Override
    public int getValue() {
        notifiedObservers = (notifiedObservers + 1) % sensor.getObservers().size();
        return value;
    }
}
