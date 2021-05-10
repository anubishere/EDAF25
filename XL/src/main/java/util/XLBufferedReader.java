package util;

import model.CellAddress;
import model.CellEntry;
import model.CommentCell;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import model.CellBuilder;

public class XLBufferedReader extends BufferedReader {
  public XLBufferedReader(File file) throws FileNotFoundException {
    super(new FileReader(file));
  }

  public void load(Map<CellAddress, CellEntry> map) throws IOException {
    map.clear(); //Jag tror iallfall att detta behövs för att "rensa ut" det gamla kalkylarket.
    try {
      while (ready()) {
        String input = readLine();
        String[] result = input.split("=");
        String cell = result[0];
        String value = result[1];

        String[] rowAndCol = cell.split("\\+"); //Delar upp addressen i row och col
        CellAddress adr = new CellAddress(Integer.parseInt(rowAndCol[0]), Integer.parseInt(rowAndCol[1]));
        CellBuilder b = new CellBuilder();
        CellEntry currentCell = b.generateCellEntry(adr, value);
        map.put(adr, currentCell);
      }
    } catch (Exception e) {
      throw new XLException(e.getMessage());
    }
  }
}
