package model;

import expr.Environment;
import expr.ExprResult;

public class EmptyCell implements CellEntry {
    public EmptyCell(){

    }

    @Override
    public ExprResult value(Environment e) {
        return 0.0;
    }

    @Override
    public String toString(){
        return "";
    }
}
