package fr.istic.coa.strategy;

import fr.istic.coa.proxy.Sensor;

/**
 * @author thomas
 * @author amona
 */
public class TimeDiffusion implements DiffusionAlgorithm {
    
    private Value value;
    private Sensor sensor;
    
    public TimeDiffusion() {
        value = new Value(0, 0);
    }
    
    @Override
    public void configure(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public void execute() {
        incrementValue();
        sensor.notifyObservers();
    }

    @Override
    public Value getValue() {
        return value;
    }
    
    private void incrementValue() {
        value.next(value.getValue() + 1);
    }
}
