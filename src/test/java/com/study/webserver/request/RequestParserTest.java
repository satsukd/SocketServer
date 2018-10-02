package com.study.webserver.request;

import com.study.webserver.TestConfigurator;
import com.study.webserver.exception.BadHttpRequest;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

public class RequestParserTest {

    RequestParser requestParser;

    @Before
    public void setUp() throws Exception {
        requestParser = new RequestParser();
    }

    @Test
    public void parseRequest() throws BadHttpRequest {

        InputStream inputStream = new ByteArrayInputStream(TestConfigurator.TEST_REQUEST.getContent().getBytes());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        Request request = requestParser.parseRequest(bufferedReader);
        assertEquals(TestConfigurator.RESOURCE_NAME.getContent(), request.getUrl());
        assertEquals(2, request.getHeaders().size());
        assertEquals("localhost:8080", request.getHeaders().get("Host"));

    }

    @Test
    public void injectURLandMethod() throws BadHttpRequest {
        String requestLine = "GET /PATH HTTP/1.1";
        Request request = new Request();
        requestParser.injectURLandMethod(request, requestLine);
        assertEquals("GET", request.getHttpMethod().name());
        assertEquals("/PATH", request.getUrl());
    }

    @Test
    public void injectHeaders() {
        String requestLine1 = "Content-Length: 68137";
        String requestLine2 = "Content-Type: multipart/form-data;";
        Request request = new Request();
        requestParser.injectHeaders(request, requestLine1);
        requestParser.injectHeaders(request, requestLine2);
        assertEquals("68137", request.getHeaders().get("Content-Length"));
        assertEquals("multipart/form-data;", request.getHeaders().get("Content-Type"));

    }
}