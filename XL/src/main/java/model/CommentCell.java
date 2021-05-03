package model;

import expr.Environment;
import expr.ExprResult;

public class CommentCell implements CellEntry {

    private String comment;

    public CommentCell(String comment){
        this.comment = comment;

    }

    @Override
    public ExprResult value(Environment e) {
        return 0.0;
    }
    @Override
    public String toString() {
        return comment;
    }
}
