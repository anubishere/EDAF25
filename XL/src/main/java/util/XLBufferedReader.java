package util;

import model.*;

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
        String input = readLine();
        String[] result = input.split("=");
        String cell = result[0];
        String value = result[1];
        CellBuilder cb = new CellBuilder();
        CellAddress cellAddress = cb.stringToAddress(cell);
        CellEntry entry = cb.generateCellEntry(cellAddress, value);
        map.put(cellAddress, entry);
      }
    } catch (Exception e) {
      throw new XLException(e.getMessage());
    }
  }
}
