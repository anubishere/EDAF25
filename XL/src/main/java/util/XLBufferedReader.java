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

public class XLBufferedReader extends BufferedReader {
  public XLBufferedReader(File file) throws FileNotFoundException {
    super(new FileReader(file));
  }

  public void load(Map<CellAddress, CellEntry> map) throws IOException {
    try {
      while (ready()) {
        String string = readLine();

        // kolla vilken typ av cell

        String result[] = string.split("=");
        String cell = result[0];
        String value = result[1];


      }
    } catch (Exception e) {
      throw new XLException(e.getMessage());
    }
  }
}
