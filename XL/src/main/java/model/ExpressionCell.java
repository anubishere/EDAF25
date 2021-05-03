package model;

import expr.Environment;
import expr.Expr;
import expr.ExprResult;

public class ExpressionCell implements CellEntry {

    Expr expression;

    public ExpressionCell(Expr expression){
        this.expression = expression;
    }

    @Override
    public ExprResult value(Environment e) {
        return expression.value(e);
    }

    @Override
    public String toString(){
        return expression.toString();
    }
}
