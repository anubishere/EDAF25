package model;

import expr.Environment;
import expr.ExprResult;
import expr.ErrorResult;

public class ErrorCell implements CellEntry {

    private String error;

    public ErrorCell(String error) {
        this.error = error;
    }

    @Override
    public ExprResult value(Environment e) throws CircularError {
        return new ErrorResult(error);

    }

    @Override
    public String toString() {
        return error;

    }
}
