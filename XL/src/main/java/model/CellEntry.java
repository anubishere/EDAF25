package model;

import expr.Environment;

public interface CellEntry {

    public double value(Environment e);

    public String toString();
}
