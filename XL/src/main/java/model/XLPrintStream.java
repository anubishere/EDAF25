package model;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map;

public class XLPrintStream extends PrintStream {
  public XLPrintStream(String fileName) throws FileNotFoundException { //"throws" might not be the correct way to do this
    super(fileName);
  }

  public void save(Map<CellAddress, CellEntry> map) {
    for (Map.Entry<CellAddress, CellEntry> entry : map.entrySet()) {
      if(!(entry.getValue().toString().equals(""))){
        print(entry.getKey());
        print('=');
        println(entry.getValue());
      }
    }
    flush();
    close();
  }
}
