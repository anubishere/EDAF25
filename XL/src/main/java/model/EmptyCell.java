package model;

import expr.Environment;
import expr.ErrorResult;
import expr.ExprResult;

public class EmptyCell implements CellEntry {
    public EmptyCell(){
    }

    @Override
    public ExprResult value(Environment e) {
        return new ErrorResult("Refers to an empty cell");
    }

    @Override
    public String toString(){
        return "";
    }
}
