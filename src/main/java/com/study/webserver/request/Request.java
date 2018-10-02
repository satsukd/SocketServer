package com.study.webserver.request;

import com.study.webserver.emun.HttpMethod;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private String url;
    private HttpMethod httpMethod;
    private Map<String, String> headers;

    public Request() {
        headers = new HashMap<>();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
