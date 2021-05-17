package util;

import model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class XLBufferedReader extends BufferedReader {
  XLModel model;
  public XLBufferedReader(File file, XLModel model) throws FileNotFoundException {
    super(new FileReader(file));
    this.model = model;
  }

  public void load(Map<CellAddress, CellEntry> map) throws IOException {
    try {
      while (ready()) {
        String input = readLine();
        String[] result = input.split("=");
        String cell = result[0];
        String value = result[1];
        CellAddress cellAddress = CellBuilder.stringToAddress(cell);
        this.model.update(cellAddress, value);
      }
    } catch (Exception e) {
      throw new XLException(e.getMessage());
    }
  }
}
