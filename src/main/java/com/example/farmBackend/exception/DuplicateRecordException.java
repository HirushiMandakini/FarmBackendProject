package com.example.farmBackend.exception;

public class DuplicateRecordException extends ServiceException {
    public DuplicateRecordException(String message) {
        super(message);
    }
}
