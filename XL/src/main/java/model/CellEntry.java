package model;

import expr.Environment;
import expr.ExprResult;

public interface CellEntry {

    public ExprResult value(Environment e);

    public String toString();
}
