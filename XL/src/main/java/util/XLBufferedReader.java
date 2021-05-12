package util;

import model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class XLBufferedReader extends BufferedReader {
  XLModel xl;
  public XLBufferedReader(File file, XLModel xl) throws FileNotFoundException {
    super(new FileReader(file));
    this.xl = xl;
  }

  public void load(Map<CellAddress, CellEntry> map) throws IOException {
    xl.clear();
    xl.updateCellMap();
    try {
      while (ready()) {
        String input = readLine();
        String[] result = input.split("=");
        String cell = result[0];
        String value = result[1];
        CellBuilder cb = new CellBuilder();
        CellAddress cellAddress = cb.stringToAddress(cell);
        CellEntry entry = cb.generateCellEntry(cellAddress, value);
        map.put(cellAddress, entry);
      }
      xl.updateCellMap();
    } catch (Exception e) {
      throw new XLException(e.getMessage());
    }
  }
}
