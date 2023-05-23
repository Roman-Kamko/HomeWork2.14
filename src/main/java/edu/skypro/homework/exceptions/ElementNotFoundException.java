package edu.skypro.homework.exceptions;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(String s) {
    }

    public ElementNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElementNotFoundException(Throwable cause) {
        super(cause);
    }

    public ElementNotFoundException() {
    }
}
