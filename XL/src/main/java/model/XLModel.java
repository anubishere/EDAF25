package model;

import expr.*;
import util.XLBufferedReader;
import util.XLException;
import util.XLPrintStream;
import expr.ExprParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class XLModel implements XLObserver, Environment {
  public static final int COLUMNS = 10, ROWS = 10;
  private Map<CellAddress, CellEntry> cellMap;
  private List<XLModelObserver> observers; // la in lista med observers för att kunna notifyAll()

  public XLModel() {
    cellMap = new HashMap<>();
    observers = new ArrayList<>();
  }

  /**
   * Called when the code for a cell changes.
   *
   * @param address address of the cell that is being edited
   * @param text    the new code for the cell - can be raw text (starting with #) or an expression
   */
  public void update(CellAddress address, String text) throws IOException {
    CellEntry newCe = CellBuilder.generateCellEntry(text);
    if (circularCheckRecursion(address.toString(), newCe)) {
      System.out.println("circular");
      cellMap.put(address, new CircularCell("##ERROR (circular)"));
    } else {
      System.out.println(address.toString());
      cellMap.put(address, newCe);
    }
  }

  private boolean circularCheckRecursion(String targetAddr, CellEntry c) {
    String expr = c.toString();

    String[] splitted = expr.split("\\+-\\*/");
    for(String s : splitted) {
      if (s.contains(targetAddr)) {
        return true;
      } else if (uglyHelperFunction(s) != null){
        return circularCheckRecursion(targetAddr, cellMap.get(CellBuilder.stringToAddress(s)));
      } else {
        return false;
      }
    }
    return false;
  }

  public static String uglyHelperFunction(String s) { //Should return the address in the string
    s.toUpperCase();
    for(int i=0; i<s.length(); i++) {
      char currentChar = s.charAt(i);
      try {
        if ((int) currentChar > 64 && (int) currentChar < 75) {
          String ret = "";
          ret += currentChar;
          try {
            if (s.charAt(i + 1) > 47 && s.charAt(i + 1) < 58) {
              ret += s.charAt(i + 1);
              if (s.length() > i + 2) {
                if (s.charAt(i + 2) > 47 && s.charAt(i + 2) < 58) {
                  ret += s.charAt(i + 2);
                  return ret;
                } else {
                  return ret;
                }
              } else {
                return ret;
              }
            }
          } catch (IndexOutOfBoundsException e) {
            System.out.println("false input");
          }
        }
      }
      catch(StringIndexOutOfBoundsException e) {
        System.out.println("false input");
      }
    }
    return null;
  }

  private boolean checkIfCircular(CellEntry newCell, CellAddress address) throws IOException {
    CellEntry previous = getEntry(address.toString());
    cellMap.put(address, new CircularCell("Circular Value"));

    try {
      newCell.value(this);
    } catch (CircularError e) {
      if (previous == null) {
        cellMap.remove(address);
      } else {
        cellMap.put(address, previous);
        return false;
      }

    }
    return true;
  }


  public void loadFile(File file) throws FileNotFoundException {
    this.clear();
    try {
      XLBufferedReader reader = new XLBufferedReader(file, this);
      reader.load(this.cellMap);
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.updateCellMap();
  }

  public void saveFile(File file) throws FileNotFoundException { //this might be wrong
    XLPrintStream print = new XLPrintStream(file.getName());
    print.save(this.cellMap);
  }


//TODO efter notifyAll() behöver update() användas

  public void clear() {
    cellMap.replaceAll((a, v) -> new EmptyCell());
    updateCellMap();
  }

  // cleara en cell
  public void clearOne(CellAddress address){
    cellMap.put(address, new EmptyCell());
    updateCellMap();
  }

  public String getCellValue(CellAddress address) {
    return "";
  }

  public String getCellName(CellAddress address) {
    return "";
  }


  public void put(CellAddress address, CellEntry cell) {
    cellMap.put(address, cell);
  }

  public CellEntry getEntry(String address) throws XLException {
    CellAddress addr = CellBuilder.stringToAddress(address);
    if(!cellMap.containsKey(addr)){
      throw new XLException(String.format("Cell %s does not exist.", addr));
    }
    return cellMap.get(addr);
  }

  @Override
  public ExprResult value(String address) throws NullPointerException, NumberFormatException, XLException, CircularError {
    CellAddress addr = CellBuilder.stringToAddress(address);
    if(!cellMap.containsKey(addr)){
      throw new XLException(String.format("Cell %s does not exist.", addr));
    }
    return cellMap.get(addr).value(this);
  }

  public boolean cellExists(String address){
    CellAddress addr = CellBuilder.stringToAddress(address);
    return cellMap.get(addr) != null;

  }

  @Override
  public void addObserver(XLModelObserver observer) {
    observers.add(observer);
  }

  @Override
  public void clearAllObservers() {
    observers.clear();

  }

  /*Notifyar alla observers om ändringar som gjorts*/
  @Override
  public void notifyObservers(String address, String newText) {
    CellBuilder cb = new CellBuilder();
    CellAddress addr = cb.stringToAddress(address);
    observers.forEach(obs -> obs.modelHasChanged(addr, newText));
  }

  public String getEntryOutput(CellEntry e) {
    if (e instanceof CommentCell) {
      return e.toString();
    } else if (e instanceof EmptyCell) {
      return "";
    } else if (e instanceof ExpressionCell) { //Vet inte om man kan använda interface här
      try {
        ExprParser parser = new ExprParser();
        Expr expr = parser.build(e.toString()); //detta är nog fel,
        ExprResult result = expr.value(this);
        System.out.println(result.getClass() + " " + result);
        if (result instanceof ErrorResult) {
          return result.toString();
        } else {
          return Double.toString(result.value()); //Returns the result of the expression
        }
      }
      catch (Exception b){
        b.printStackTrace(); //placeholder
      }
    } else if (e instanceof CircularCell){
      return e.toString();
    } else if(e instanceof ErrorCell){
      return e.toString();
    }
    return "Cell is not an instance of any of the above types";
  }

  //Updates the cellMap when a change occurs
  public void updateCellMap(){
    cellMap.forEach((address, value) -> {
      String entryOutput = getEntryOutput(value);
      notifyObservers(address.toString(), entryOutput);
    });
  }

}

