package com.example.singleton;

/**
 * Created by tordly on 03.02.14.
 */
public interface Observable {

    public void registerObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void notifyObservers();
}
