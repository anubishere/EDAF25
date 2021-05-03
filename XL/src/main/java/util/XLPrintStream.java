package util;

import model.CellAddress;
import model.CellEntry;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class XLPrintStream extends PrintStream {
  public XLPrintStream(String fileName) throws FileNotFoundException { //"throws" might not be the correct way to do this
    super(fileName);
  }

  public void save(Map<CellAddress, CellEntry> map) {
    for (Map.Entry<CellAddress, CellEntry> entry : map.entrySet()) {
      print(entry.getKey());
      print('=');
      println(entry.getValue());
    }
    flush();
    close();
  }
}
