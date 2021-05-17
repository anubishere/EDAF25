package model;

import expr.ExprParser;
import util.XLException;
import model.XLModel;

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
        String s = XLModel.uglyHelperFunction(address);
        int col = (int)s.charAt(0)-65;
        String row = "";
        row += s.charAt(1);
        if (s.length()>2) {
            row+=s.charAt(2);
        }
        return new CellAddress(col, Integer.parseInt(row.toString()));
    }



}
