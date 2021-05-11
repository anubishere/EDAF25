package model;

import expr.ErrorResult;
import expr.ExprResult;
import expr.Environment;

public class CircularCell implements CellEntry {

    private String circular;

    public CircularCell(String circular) throws CircularError {
        this.circular = circular;

    }

    @Override public ExprResult value(Environment e) throws CircularError {
        throw new Error("Circular Value");

    }

    @Override
    public String toString() {
        return circular;
    }
}