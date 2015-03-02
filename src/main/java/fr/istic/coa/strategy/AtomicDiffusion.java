package fr.istic.coa.strategy;

import fr.istic.coa.proxy.Sensor;

/**
 * @author thomas
 * @author amona
 */
public class AtomicDiffusion implements DiffusionAlgorithm {

    private Sensor sensor;
    private Value value;
    private int notifiedObservers;
    
    public AtomicDiffusion() {
        value = new Value(0);
        notifiedObservers = 0;
    }

    @Override
    public void configure(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public void execute() {
        if (notifiedObservers == 0) {
            incrementValue();
            sensor.notifyObservers();
        }
    }

    @Override
    public Value getValue() {
        notifiedObservers = (notifiedObservers + 1) % sensor.getObservers().size();
        return value;
    }

    private void incrementValue() {
        value.setValue(value.getValue() + 1);
    }
}
