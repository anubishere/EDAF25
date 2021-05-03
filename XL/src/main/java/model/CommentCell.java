package model;

import expr.Environment;
import expr.ExprResult;
import expr.ValueResult;

public class CommentCell implements CellEntry {

    private String comment;

    public CommentCell(String comment){
        this.comment = comment;

    }

    @Override
    public ExprResult value(Environment e) {
        ValueResult empty = new ValueResult(0.0);
        return empty;
    }
    @Override
    public String toString() {
        return comment;
    }
}
