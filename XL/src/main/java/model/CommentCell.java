package model;

import expr.Environment;

public class CommentCell implements CellEntry {

    private String comment;

    public CommentCell(String comment){
        this.comment = comment;

    }

    public String getComment(){
        return comment;
    }

    @Override
    public double value(Environment e) {
        return 0.0;
    }
}
