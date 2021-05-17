package model;


import expr.ErrorResult;
import expr.Environment;
import expr.ExprResult;
import util.XLException;

public class CircularCell implements CellEntry {

    private CellEntry c;

    public CircularCell(CellEntry c) {
        this.c = c;

    }

    @Override public ExprResult value(Environment e) throws XLException {
       if(c instanceof CellEntry){
           try {
               c.value(e);
           } catch (StackOverflowError L){
               throw new XLException("Circular reference");
           }
       }
       return c.value(e);

    }


    @Override
    public String toString() {
        return "Circular";
    }
}