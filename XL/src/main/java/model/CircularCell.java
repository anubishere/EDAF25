package model;


import expr.ErrorResult;
import expr.Environment;
import expr.ExprResult;
import util.XLException;

public class CircularCell implements CellEntry {

    private String circular;

    public CircularCell(String circular) {
        this.circular = circular;
    }

    @Override public ExprResult value(Environment e) throws CircularError {
        return new ErrorResult(circular);
    }

    @Override
    public String toString(){
        return circular;
    }
}