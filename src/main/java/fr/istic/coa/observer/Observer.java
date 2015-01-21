package fr.istic.coa.observer;

/**
 * @author thomas
 * @author amona
 */
public interface Observer<T extends Observable> {
    public void update(T subject);
}
