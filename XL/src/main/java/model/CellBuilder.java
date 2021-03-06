package model;

import expr.ErrorExpr;
import expr.ExprParser;

public class CellBuilder {

    /*
    Generates a cellEntry from text input. Infers the Cell type using the input.
     */
    public static CellEntry generateCellEntry(String text){
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
                return new ExpressionCell(new ErrorExpr(e.getMessage())); //Incalculable expressions are return as Expressioncell with ErrorExpr in them.
            }
        }
    }

    public static CellAddress stringToAddress(String address) { //Converts a string in address form to a CellAddress.
        String s = XLModel.uglyHelperFunction(address); //String s now contains ONLY the address.
        int col = (int)s.charAt(0)-65; //First char is the letter, and CellAddress takes an integer, not char to determine columns.
        String row = "";
        row += s.charAt(1); //Gets the first number in the address
        if (s.length()>2) { //If the address contains a 2nd number, append it to row aswell.
            row+=s.charAt(2);
        }
        return new CellAddress(col, Integer.parseInt(row)); //Return the CellAddresss.
    }
}
