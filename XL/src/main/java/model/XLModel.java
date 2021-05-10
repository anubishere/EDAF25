package model;

import expr.ExprResult;
import javafx.beans.InvalidationListener;
import util.XLBufferedReader;
import util.XLException;
import util.XLPrintStream;
import expr.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class XLModel extends Observable implements Environment {

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
  public void update(CellAddress address, String text) throws IOException {

    CellBuilder cb = new CellBuilder();
    CellEntry cell = cb.generateCellEntry(address, text);
    cellMap.put(address, cell);
    String test;
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

  /*Sätter tomma celler på alla platser i mappen*/

  public void clearAll() {
    CellEntry empty = new EmptyCell();

    for(int i = 0; i < cellMap.size(); i++){
      CellEntry c = cellMap.get(i);
      c = new EmptyCell();
    }
    notifyAll();          // uppdatera efter allt är clearat
  }

  public void clearOne(String name){
    if(cellMap.containsKey(name)){
      CellEntry temp = cellMap.get(name);
      cellMap.remove(name);
    }
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

  @Override
  public ExprResult value(String address) throws NullPointerException, NumberFormatException, XLException {
    if(cellMap.get(address) == null){
      throw new XLException(String.format("Cell %s doest not exist.", address));          //%S är address
    }
    return cellMap.get(address).value(this);
  }

  public boolean cellExists(String address){
    return cellMap.get(address) != null;

  }
  //TODO fixa metod som returnerar text i en cell




}

