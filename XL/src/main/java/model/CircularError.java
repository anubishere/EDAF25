package model;

public class CircularError extends Error {

    private String value;

    public CircularError(String value) {
        super(value);

    }
}
