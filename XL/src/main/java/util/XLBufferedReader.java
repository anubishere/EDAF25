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
      while (ready()) {
        String string = readLine();
        String result = string.split("=");
        String cell = result[0];
        String value = result[1];
        map.put(cell, value);
      }
    } catch (Exception e) {
      throw new XLException(e.getMessage());
    }
  }
}
