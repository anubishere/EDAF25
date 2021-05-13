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

public class XLModel implements ObservableModel, Environment {
  public static final int COLUMNS = 10, ROWS = 10;
  private Map<CellAddress, CellEntry> cellMap;
  private List<ModelObserver> observers; // la in lista med observers för att kunna notifyAll()

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
    ExprParser ex = new ExprParser();
    CellEntry ce = getEntry(address.toString());

    if(ce instanceof ExpressionCell && !text.equals("")){
      ExpressionCell newCell = new ExpressionCell(ex.build(text));
      cellMap.put(address, new CircularCell(text));
      try {
        newCell.value(this);
        cellMap.put(address, newCell);
      } catch (CircularError e) { //Hantering av cirkulära fel.
        cellMap.put(address, new ErrorCell(e.getMessage()));
      }
    }
    else{
      CellBuilder cb = new CellBuilder();
      CellEntry cell = cb.generateCellEntry(address, text);
      cellMap.put(address, cell);
    }
  }

  public void loadFile(File file) throws FileNotFoundException {
    this.clear();
    try {
      XLBufferedReader reader = new XLBufferedReader(file);
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
  public void addObserver(ModelObserver observer) {
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

    observers.forEach(obs -> {
      obs.modelHasChanged(addr, newText);
    });

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
      return "Circular error"; //Error här egentligen?
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

