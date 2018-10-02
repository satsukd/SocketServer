package com.study.webserver.emun;

import org.junit.Test;

import static org.junit.Assert.*;

public class HttpMethodTest {

    @Test
    public void getMessage() {
        assertEquals(HttpMethod.valueOf("GET").name(), "GET");
        assertEquals(HttpMethod.valueOf("POST").name(), "POST");
    }

}