package com.example.project.exception;

public class EntryNotFoundException extends RuntimeException {
    public EntryNotFoundException() {
        super("Entry not found.");
    }
}
