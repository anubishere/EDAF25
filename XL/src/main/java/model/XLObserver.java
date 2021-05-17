package model;

public interface XLObserver {

    /*
    * Lägger till en observer som ska notifieras när något är ändrat */
    void addObserver(XLModelObserver observer);

    /* address är addressen för den cell vi ska berätta att värdet ändrats
        newText är det värdet som cellen nu innehåller
        */
    void notifyObservers(String address, String newText);
}
