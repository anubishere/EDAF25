package expr;

import model.CircularError;
import util.XLException;

class Variable implements Expr {
  private String name;

  Variable(String name) {
    this.name = name;
  }

  @Override public String toString() {
    return name;
  }

  public ExprResult value(Environment env) {
    try {
      return env.value(name);
    } catch (XLException | CircularError e) {
      e.printStackTrace();
      return null;
    }
  }
}
