package com.study.webserver;

public enum TestConfigurator {
    WEB_APP_PATH("src\\main\\resources"),
    RESOURCE_NAME("/index.html"),
    ON_SUCCESS_HEADER(" HTTP/1.0 200 OK\n"),
    TEST_REQUEST("GET " + TestConfigurator.RESOURCE_NAME.getContent() + " HTTP/1.1\n" +
           "Host: localhost:8080\n" +
           "Upgrade-Insecure-Requests: 1\n");


    private String content;

    TestConfigurator(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
