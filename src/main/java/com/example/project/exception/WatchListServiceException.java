package com.example.project.exception;

public class WatchListServiceException extends RuntimeException {
    public WatchListServiceException() {
        super("Watch list not found");
    }
}
