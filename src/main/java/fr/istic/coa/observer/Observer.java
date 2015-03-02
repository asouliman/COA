package fr.istic.coa.observer;

import fr.istic.coa.strategy.Value;

import java.util.concurrent.Future;

/**
 * @author thomas
 * @author amona
 */
public interface Observer<T extends Observable> {
    public void update(T subject);
    public Future<Integer> updateAsync(T subject);
}
