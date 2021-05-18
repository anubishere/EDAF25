package model;

import expr.*;

public class ExpressionCell implements CellEntry {

    private Expr expression;

    public ExpressionCell(Expr expression){
        this.expression = expression;
    }

    @Override
    public ExprResult value(Environment e) {
        try{
            return expression.value(e);
        }catch(Exception ex){
            expression = new ErrorExpr("Cell is out of bounds");
            return expression.value(e);
        }
    }
    @Override
    public String toString(){
        return expression.toString();
    }
}
