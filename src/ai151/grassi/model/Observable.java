package ai151.grassi.model;

/**
 * Created by AGrassi on 24.04.2017.
 */
public interface Observable {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
