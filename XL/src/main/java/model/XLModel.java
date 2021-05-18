package model;

import expr.*;
import gui.GridCell;
import javafx.scene.layout.GridPane;
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
    private final Map<CellAddress, CellEntry> cellMap;
    private final List<XLModelObserver> observers; // la in lista med observers för att kunna notifyAll()

    public XLModel() {
        cellMap = new HashMap<>();
        observers = new ArrayList<>();

        for (int r = 0; r < XLModel.ROWS; ++r) {
            for (int c = 0; c < XLModel.COLUMNS; ++c) {
                CellAddress address = new CellAddress(c, r+1); //r+1 to compensate for the fact that rows start at 1.
                put(address, new EmptyCell()); //Fyller modelmappen med tomma celler.
            }
        }
    }

    /**
     * Called when the code for a cell changes.
     *
     * @param address address of the cell that is being edited
     * @param text    the new code for the cell - can be raw text (starting with #) or an expression
     */
    public void update(CellAddress address, String text) throws IOException {
        CellEntry newCe = CellBuilder.generateCellEntry(text);
            if (circularCheckRecursion(address.toString(), newCe) ) {
                cellMap.put(address, new CircularCell("##ERROR (Circular Error)"));
            } else {
                cellMap.put(address, newCe);
            }

    }
    /*
    Recursive method used to check if a cell is circular.
     */
    private boolean circularCheckRecursion(String targetAddr, CellEntry c) {
        String expr = c.toString(); //gets all cell contents
        String[] splitted = expr.split("\\+-\\*/"); //splits the cell into their parts.
        for (String s : splitted) {
            if (s.contains(targetAddr)) { //If a cell contains the starting cell, it's circular.
                return true;
            } else if (uglyHelperFunction(s) != null) { //If the cell contents contains another address, check that address for a circular reference to the origin cell.
                return circularCheckRecursion(targetAddr, cellMap.get(CellBuilder.stringToAddress(s)));
            }
        }
        return false;
    }

    /*
    If String s contains an address, returns the address. It not, returns null.
     */
    public static String uglyHelperFunction(String s ){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) { //Iterates over the string
            char currentChar = s.charAt(i);
            if ((int) currentChar > 64 && (int) currentChar < 75) { //If current character is a letter, check if it has 1 or 2 following numbers
                sb.append(currentChar);
                if (s.charAt(i + 1) > 47 && s.charAt(i + 1) < 58) { //checks the first following number (A5 for example)
                    sb.append(s.charAt(i + 1)); //Appends if it's a number, otherwise it's not an address and null will be returned.

                    if (s.length() > i + 2 && s.charAt(i + 2) > 47 && s.charAt(i + 2) < 58) { //If it has another number, append it (for exampleA10), if not, return ret. (for example A9)
                        sb.append(s.charAt(i + 2)); //Appends if it's a number
                    }
                    return sb.toString();
                }
            }
        }
        return null;
    }

    public void loadFile(File file) throws FileNotFoundException {
        this.clear();
        try {
            XLBufferedReader reader = new XLBufferedReader(file, this);
            reader.load(this.cellMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateCellMap();
    }

    public void saveFile(File file) throws FileNotFoundException { //this might be wrong
        XLPrintStream print = new XLPrintStream(file.getName());
        print.save(this.cellMap);
    }

    public void clear() {
        cellMap.replaceAll((a, v) -> new EmptyCell());
        updateCellMap();
    }

    /*
    Used to clear a single cell.
     */
    public void clearOne(CellAddress address) {
        cellMap.put(address, new EmptyCell());
        updateCellMap();
    }

    public void put(CellAddress address, CellEntry cell) {
        cellMap.put(address, cell);
    }

    @Override
    public ExprResult value(String address) throws NullPointerException, NumberFormatException, XLException {
        CellAddress addr = CellBuilder.stringToAddress(address);
        if (!cellMap.containsKey(addr)) {
            throw new XLException(String.format("Cell %s does not exist.", addr));
        }
        return cellMap.get(addr).value(this);
    }

    public CellEntry getEntry(String address) {
        CellAddress addr = null;
        try{
            addr = CellBuilder.stringToAddress(address);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return cellMap.get(addr);
    }
    /*
    Used to determine the output of a given cell
     */
    public String getEntryOutput(CellEntry e) throws XLException {
        try{
            ExprParser parser = new ExprParser();
            parser.build(e.toString());
            return e.value(this).toString();
        }catch(Exception ef){
            return e.toString();
        }
    }

    @Override
    public void addObserver(XLModelObserver observer) { observers.add(observer); }

    /*
     Notifies all observers about changes that have been made.
    */
    @Override
    public void notifyObservers(String address, String newText) {
        CellAddress addr = CellBuilder.stringToAddress(address);
        observers.forEach(obs -> obs.modelHasChanged(addr, newText));
    }

    //Updates the cellMap when a change occurs
    public void updateCellMap() {
        cellMap.forEach((address, value) -> {
            try {
                notifyObservers(address.toString(), getEntryOutput(value));
            } catch (XLException e) {
                e.printStackTrace();
            }
        });
    }
}

