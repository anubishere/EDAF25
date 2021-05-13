package model;

import expr.ExprParser;
import util.XLException;

import java.io.IOException;

public class CellBuilder {

    public static CellEntry generateCellEntry(String text) throws IOException {
        if(text.equals("")){
            return new EmptyCell();
        }
        else if(text.startsWith("#")){
            return new CommentCell(text.substring(1));
        }
        else {
            try{
                ExprParser ex = new ExprParser();
                return new ExpressionCell(ex.build(text));
            }catch(Exception e){ //Catches instances of expressions that are incalculable
                return new ErrorCell(e.getMessage());
            }
        }
    }

    public static CellAddress stringToAddress(String address) {
        char firstChar = address.charAt(0);
        int col = (int)firstChar - 65;
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<address.length(); i++) {
            sb.append(address.charAt(i));
        }
        return new CellAddress(col, Integer.parseInt(sb.toString()));
    }


}
