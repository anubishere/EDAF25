package model;

import expr.Environment;
import expr.ExprResult;
import util.XLException;

public interface CellEntry {

    public ExprResult value(Environment e) throws CircularError, XLException;

    public String toString();
}
