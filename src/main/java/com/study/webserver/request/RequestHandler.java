package com.study.webserver.request;

import com.study.webserver.exception.BadHttpRequest;
import com.study.webserver.exception.NotFoundResponse;
import com.study.webserver.response.ResourceReader;
import com.study.webserver.response.ResponseWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class RequestHandler {
    private BufferedReader socketReader;
    private BufferedWriter socketWriter;
    private String webAppPath;

    public RequestHandler(BufferedReader socketReader, BufferedWriter socketWriter, String webAppPath) {
        this.socketReader = socketReader;
        this.socketWriter = socketWriter;
        this.webAppPath = webAppPath;
    }

    public void handle() throws IOException {
        RequestParser requestParser = new RequestParser();
        ResponseWriter responseWriter = new ResponseWriter();

        Request request = null;
        try {
            request = requestParser.parseRequest(socketReader);
            ResourceReader resourceReader = new ResourceReader();
            String resourceContent = resourceReader.getResource(request, webAppPath);
            responseWriter.writeSuccessResponse(socketWriter, resourceContent);
        } catch (BadHttpRequest badHttpRequest) {
            badHttpRequest.printStackTrace();
            responseWriter.writeBadRequestResponse(socketWriter);
        } catch (NotFoundResponse notFoundResponse) {
            notFoundResponse.printStackTrace();
            responseWriter.writeNotFoundResponse(socketWriter);
        }

    }
}
