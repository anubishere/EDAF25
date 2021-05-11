package model;

public class CircularError extends Exception {

    private String error;

    public CircularError(String error) {
        this.error = error;

    }

    private String getError() {
        return error;
        
    }
}
