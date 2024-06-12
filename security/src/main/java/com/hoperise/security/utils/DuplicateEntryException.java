package com.hoperise.security.utils;

public class DuplicateEntryException extends RuntimeException {
    public DuplicateEntryException(String errorMessage) {
        super(errorMessage);
    }
}