package fr.istic.coa.observer;

/**
 * @author thomas
 * @author amona
 */
public interface Observable<T extends Observer> {
    public void addObserver(T o);
    public void removeObserver(T o);
    public void notifyObservers();
}
