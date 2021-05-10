package model;

import expr.ErrorResult;
import expr.ExprResult;
import expr.Environment;

public class CircularCell implements CellEntry  {

    @Override public ExprResult value(Environment e) {
        throw new Error("Circular Value");

    }

}
