package model;

import expr.Environment;
import expr.ExprResult;
import expr.ErrorResult;

public class ErrorCell implements CellEntry {

    @Override
    public ExprResult value(Environment e) throws CircularError {
        return new ErrorResult("Error");
    }

    @Override
    public String toString() {
        return new ErrorResult("Error");
    }
}
