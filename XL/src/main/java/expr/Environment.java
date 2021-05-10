package expr;

import model.CircularError;
import util.XLException;

public interface Environment {
  ExprResult value(String name) throws XLException, CircularError;
}
