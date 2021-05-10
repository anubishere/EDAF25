package model;

import expr.Environment;
import expr.ExprResult;
import expr.ValueResult;

public class EmptyCell implements CellEntry {
    public EmptyCell(){
    }

    @Override
    public ExprResult value(Environment e) {
        ValueResult empty = new ValueResult(0.0);
        return empty;
    }

    @Override
    public String toString(){
        return "";
    }
}
