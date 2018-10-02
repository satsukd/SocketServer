package com.study.webserver.exception;

public class NotFoundResponse extends Exception {
    public NotFoundResponse(String message) {
        super(message);
    }
}
