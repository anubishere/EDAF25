package model;

import util.XLBufferedReader;
import util.XLPrintStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class XLModel implements Environment {
  public static final int COLUMNS = 10, ROWS = 10;
  private Map<CellAddress, CellEntry> cellMap;

  public XLModel() {
    cellMap = new HashMap<>();
  }

  /**
   * Called when the code for a cell changes.
   *
   * @param address address of the cell that is being edited
   * @param text    the new code for the cell - can be raw text (starting with #) or an expression
   */
  public void update(CellAddress address, String text) {
  }

  public void loadFile(File file) throws FileNotFoundException {
    try {
      XLBufferedReader reader = new XLBufferedReader(file);
      reader.load(this.cellMap);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void saveFile(File file) throws FileNotFoundException { //this might be wrong
    XLPrintStream print = new XLPrintStream(file.getName());
    print.save(this.cellMap);
  }

  public void clear() {

  }

  public String getCellValue(CellAddress address) {
    return "";
  }

  public String getCellName(CellAddress address) {
    return "";
  }
  public void remove(CellAddress address) {

  }

  public void put(CellAddress address, CellEntry cell) {
    cellMap.put(address, cell);
  }

}

