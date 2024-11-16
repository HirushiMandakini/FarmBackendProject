package com.example.farmBackend.exception;

public class DataPersistException extends RuntimeException{

    public DataPersistException() {
        super();
    }

    public DataPersistException(String message, Throwable cause) {
        super(message, cause);
    }
    public DataPersistException(String message) {
        super(message);
    }
}
