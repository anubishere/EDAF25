package model;

import expr.ErrorResult;
import expr.ExprResult;
import expr.Environment;

public class CircularCell implements CellEntry {

    private String circular;

    public CircularCell(String circular) {
        this.circular = circular;

    }

    @Override public ExprResult value(Environment e) throws CircularError {
        throw new CircularError("Circular Value");

    }

    @Override
    public String toString() {
        return circular;
    }
}