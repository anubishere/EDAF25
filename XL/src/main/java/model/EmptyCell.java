package model;

import expr.Environment;
import expr.ErrorResult;
import expr.ExprResult;
import expr.ValueResult;

public class EmptyCell implements CellEntry {
    public EmptyCell(){
    }

    @Override
    public ExprResult value(Environment e) {
        ErrorResult error = new ErrorResult("refers to empty");
        return error;
    }

    @Override
    public String toString(){
        return "";
    }
}
