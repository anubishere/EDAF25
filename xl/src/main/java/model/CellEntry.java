package model;

import expr.Environment;

public interface CellEntry {

    double value(Environment e);
    String toString();

    }

