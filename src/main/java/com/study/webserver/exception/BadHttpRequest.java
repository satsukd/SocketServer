package com.study.webserver.exception;

public class BadHttpRequest extends Exception {
    public BadHttpRequest(String message) {
        super(message);
    }
}
