package edu.skypro.homework.exceptions;

public class StorageIsFullException extends RuntimeException{
    public StorageIsFullException() {
    }

    public StorageIsFullException(String message) {
        super(message);
    }

    public StorageIsFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public StorageIsFullException(Throwable cause) {
        super(cause);
    }
}
