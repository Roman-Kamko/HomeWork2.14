package edu.skypro.homework.exceptions;

public class BadIndexException extends RuntimeException{
    public BadIndexException() {
    }

    public BadIndexException(String message) {
        super(message);
    }

    public BadIndexException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadIndexException(Throwable cause) {
        super(cause);
    }
}
