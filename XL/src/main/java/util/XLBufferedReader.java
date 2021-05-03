package util;

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

  public void load(Map<String, CellEntry> map) throws IOException {
    try {
      for (int i=65; i<75; i++) {
        for (j=0; j<10; j++) {
          map.put((char)i + "=" + j, new EmptyCell());
        }
      }
      while (ready()) {
        String string = readLine();
        String result = string.split("=");
        String cell = result[0];
        String value = result[1];
        map.replace(cell, value);
      }
    } catch (Exception e) {
      throw new XLException(e.getMessage());
    }
  }
}
