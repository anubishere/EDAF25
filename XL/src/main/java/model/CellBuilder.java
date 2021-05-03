package model;

import expr.ExprParser;

import java.io.IOException;

public class CellBuilder {

    public CellEntry generateCellEntry(CellAddress address, String text) throws IOException {
        if(text == " "){
            return new EmptyCell();
        }
        else if(text.startsWith("#")){
            return new CommentCell(text.substring(1));
        }
        else {
            ExprParser ex = new ExprParser();
            return new ExpressionCell(ex.build(text));
        }
    }
}
