package expr;

import util.XLException;

public interface Environment {
  ExprResult value(String name) throws XLException;
}
