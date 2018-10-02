package com.study.webserver.request;

import com.study.webserver.emun.HttpMethod;
import com.study.webserver.exception.BadHttpRequest;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestParser {

    private static final String methodDelimeter = "/";
    private static final String headerDelimeter = ":";

    public Request parseRequest(BufferedReader socketReader) throws BadHttpRequest {
        Request request = new Request();
        try {
            String firstLine = socketReader.readLine();
            injectURLandMethod(request, firstLine);

            String header;
            while ((header = socketReader.readLine()) != null) {
                if ("".equalsIgnoreCase(header) || "\n".equalsIgnoreCase(header)) {
                    break;
                }
                injectHeaders(request, header);
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new BadHttpRequest("Invalid request");
        }

        return request;
    }

    void injectURLandMethod(Request request, String line) throws BadHttpRequest {
        if (line == null || line.isEmpty()) {
            throw new BadHttpRequest("Empty request");
        }
        String URL = line.substring(line.indexOf(methodDelimeter), line.indexOf(" HTTP/1.1")).trim();
        request.setUrl(URL);
        String methodString = line.substring(0, line.indexOf(methodDelimeter)).trim();
        HttpMethod method = HttpMethod.valueOf(methodString);
        if (method == null) {
            throw new BadHttpRequest("Inavlid method");
        }
        request.setHttpMethod(method);
    }

    void injectHeaders(Request request, String line) {
        String headerKey = line.substring(0, line.indexOf(headerDelimeter)).trim();
        String headerValue = line.substring(line.indexOf(headerDelimeter) + 1).trim();
        request.getHeaders().put(headerKey, headerValue);
    }
}
