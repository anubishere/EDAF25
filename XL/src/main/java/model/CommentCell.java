package model;

import expr.Environment;
import expr.ErrorResult;
import expr.ExprResult;
import expr.ValueResult;

public class CommentCell implements CellEntry {

    private String comment;

    public CommentCell(String comment){
        this.comment = comment;

    }

    @Override
    public ExprResult value(Environment e) {
        ErrorResult error = new ErrorResult("references comment");
        return error;
    }
    @Override
    public String toString() {
        return comment;
    }
}
