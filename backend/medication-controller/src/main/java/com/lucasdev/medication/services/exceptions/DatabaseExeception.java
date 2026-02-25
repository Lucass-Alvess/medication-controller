package com.lucasdev.medication.services.exceptions;

public class DatabaseExeception extends RuntimeException {
    public DatabaseExeception(String message) {
        super(message);
    }
}
