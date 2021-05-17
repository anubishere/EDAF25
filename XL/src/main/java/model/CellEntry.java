package model;

import expr.Environment;
import expr.ExprResult;
import util.XLException;

public interface CellEntry {

    ExprResult value(Environment e) throws XLException;

    String toString();
}
