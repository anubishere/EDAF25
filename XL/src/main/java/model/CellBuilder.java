package model;

import expr.ExprParser;

import java.io.IOException;

public class CellBuilder {

    public CellEntry generateCellEntry(CellAddress address, String text) throws IOException {
        if(text.equals("")){
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

    public static CellAddress stringToAddress(String address) {
        char firstChar = ((char) address.charAt(0));
        int col = (int)firstChar - 65;
        String row = "";
        for (int i=1; i<address.length(); i++) {
            row += address.charAt(i);
        }
        return new CellAddress(col, Integer.parseInt(row));
    }

    public static String getEntryOutput(CellEntry e) {
        if (e instanceof CommentCell) {
            return e.toString();
        } else if (e instanceof EmptyCell) {
            return "";
        } else if (e instanceof expr.Expr) { //Vet inte om man kan använda interface här
            //return e.value().toString(); placeholder, vet inte hur detta ska funka
            return "vet inte vad här ska vara";
        } else if (e instanceof CircularCell){
            return ""; //Error här egentligen?
        }
        return "error";
    }
}
