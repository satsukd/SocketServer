package com.study.webserver.emun;

import org.junit.Test;

import static org.junit.Assert.*;

public class HttpResponseCodeTest {

    @Test
    public void getMessage() {
        assertEquals(HttpResponseCode.valueOf("OK").name(), "OK");
        assertEquals(HttpResponseCode.valueOf("BAD_REQUEST").name(), "BAD_REQUEST");
        assertEquals(HttpResponseCode.valueOf("NOT_FOUND").name(), "NOT_FOUND");
        assertEquals(HttpResponseCode.valueOf("INTERNAL_SERVER_ERROR").name(), "INTERNAL_SERVER_ERROR");
        assertEquals(HttpResponseCode.valueOf("OK").getMessage(), "HTTP/1.0 200 OK\n");
        assertEquals(HttpResponseCode.valueOf("BAD_REQUEST").getMessage(), "HTTP/1.0 400 Bad Request\n");
        assertEquals(HttpResponseCode.valueOf("NOT_FOUND").getMessage(), "HTTP/1.0 404 Not Found\n");
        assertEquals(HttpResponseCode.valueOf("INTERNAL_SERVER_ERROR").getMessage(), "HTTP/1.0 500 Internal Server Error\n");
    }
}