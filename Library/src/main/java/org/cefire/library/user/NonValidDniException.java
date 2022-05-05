package org.cefire.library.user;

public class NonValidDniException extends RuntimeException {
    public NonValidDniException(Throwable cause) {
        super(cause);
    }

    public NonValidDniException() {
    }
}
