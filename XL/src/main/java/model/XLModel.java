package model;

import util.XLBufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class XLModel {
  public static final int COLUMNS = 10, ROWS = 10;
  private Map<String, CellEntry> cellMap;

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
    XLBufferedReader reader = new XLBufferedReader(file);
  }

  public void saveFile(File file) {
  }

  public void clear() {

  }

  public String getCellValue(CellAddress address) {

  }

  public String getCellName(CellAddress address) {

  }
  public void remove(CellAddress address) {

  }

  public void put(CellAddress address, CellEntry cell) {

  }

}

