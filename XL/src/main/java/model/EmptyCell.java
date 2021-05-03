package model;

import expr.Environment;

public class EmptyCell implements CellEntry {
    public EmptyCell(){

    }

    @Override
    public double value(Environment e) {
        return 0.0;
    }

    @Override
    public String toString(){
        return "";
    }
}
