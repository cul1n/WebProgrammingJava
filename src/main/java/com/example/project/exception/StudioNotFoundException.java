package com.example.project.exception;

public class StudioNotFoundException extends RuntimeException{
    public StudioNotFoundException() {
        super("Studio not found.");
    }
}
