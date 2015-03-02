package fr.istic.coa.strategy;

import fr.istic.coa.proxy.Sensor;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author thomas
 * @author amona
 */
public class SequenceDiffusion implements DiffusionAlgorithm {

    private Sensor sensor;
    private Value value;
    private int notifiedObservers;
    private Queue<Value> pendingValues;

    public SequenceDiffusion() {
        value = new Value(0);
        notifiedObservers = 0;
        pendingValues = new LinkedList<>();
    }
    
    @Override
    public void configure(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public void execute() {
        // If all observers have been notified
        if (notifiedObservers == 0) {
            // Remove the oldest pending value
            pendingValues.poll();
            
            // Add the new value to the queue
            pendingValues.add(value);
            incrementValue();
            
            sensor.notifyObservers();
        } else {
            // Else, just add the new value to the queue
            pendingValues.add(value);
            incrementValue();
        }
    }

    @Override
    public Value getValue() {
        notifiedObservers = (notifiedObservers + 1) % sensor.getObservers().size();
        
        // Return the oldest pending value
        return pendingValues.peek();
    }
    
    private void incrementValue() {
        value.setValue(value.getValue() + 1);
    }
}
