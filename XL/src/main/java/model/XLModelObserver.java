package model;

public interface XLModelObserver {


    /* @param cell är addressen för den cell där värdet har ändrats
    * @param val är värdet som den cellen har
    * */
    void modelHasChanged(CellAddress cell, String val);
}
