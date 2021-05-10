package model;

public interface ModelObserver {

    void modelHasChanged(CellAddress cell, String val);
}
