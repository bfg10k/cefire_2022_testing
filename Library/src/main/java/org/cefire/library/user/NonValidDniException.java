package org.cefire.library.user;

public class NonValidDniException extends RuntimeException {
    public NonValidDniException(NumberFormatException exception) {
        super(exception);
    }
    public NonValidDniException() {
        super();
    }
}
