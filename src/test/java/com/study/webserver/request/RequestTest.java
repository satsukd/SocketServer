package com.study.webserver.request;

import com.study.webserver.TestConfigurator;
import com.study.webserver.emun.HttpMethod;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestTest {

    Request request;

    @Before
    public void setUp() {
        request = new Request();
    }

    @Test
    public void getEmptyUrl() {
        assertTrue(request.getUrl() == null);
    }

    @Test
    public void setUrl() {
        String url = TestConfigurator.RESOURCE_NAME.getContent();
        request.setUrl(url);
        assertEquals(url, request.getUrl());
    }

    @Test
    public void getemptyHttpMethod() {
        assertTrue(request.getHttpMethod() == null);
    }

    @Test
    public void setHttpMethod() {
        request.setHttpMethod(HttpMethod.POST);
        assertTrue(request.getHttpMethod() == HttpMethod.POST);

    }

    @Test
    public void getEmptyHeaders() {
        assertTrue(request.getHeaders().isEmpty());
    }

    @Test
    public void setHeaders() {
        request.getHeaders().put("TEST", "test");
        assertEquals("test", request.getHeaders().get("TEST"));
    }
}