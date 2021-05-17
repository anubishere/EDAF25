package model;

import expr.ErrorResult;
import expr.Environment;
import expr.ExprResult;

public class CircularCell implements CellEntry {

    private String circular;

    public CircularCell(String circular) {
        this.circular = circular;
    }

    @Override public ExprResult value(Environment e) {
        return new ErrorResult("Circular Error");
    }

    @Override
    public String toString(){
        return circular;
    }
}