package model;


public class CircularError extends Throwable {

    private String error;

    public CircularError(String error) {
        this.error = error;

    }

    private String getError() {
        return error;

    }
}
