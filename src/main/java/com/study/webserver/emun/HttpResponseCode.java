package com.study.webserver.emun;

public enum HttpResponseCode {
    OK("HTTP/1.0 200 OK\n"),
    BAD_REQUEST("HTTP/1.0 400 Bad Request\n"),
    NOT_FOUND("HTTP/1.0 404 Not Found\n"),
    INTERNAL_SERVER_ERROR("HTTP/1.0 500 Internal Server Error\n");

    private String message;
    HttpResponseCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
