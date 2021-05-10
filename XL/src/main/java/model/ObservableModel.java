package model;

public interface ObservableModel {


    void addObserver(ModelObserver observer);

    void clearAllObservers();

    void notifyObservers(String address, String newText);
}
