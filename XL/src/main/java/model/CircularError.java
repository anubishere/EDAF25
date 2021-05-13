package model;


public class CircularError extends Throwable {

    private String value;

    public CircularError(String value) {
        super(value);

    }
}
