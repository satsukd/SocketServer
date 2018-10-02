package com.study.webserver.response;

import com.study.webserver.emun.HttpResponseCode;

import java.io.BufferedWriter;
import java.io.IOException;

public class ResponseWriter {
    private static final String EOL = "\n";

    public void writeSuccessResponse(BufferedWriter socketWriter, String content) throws IOException {
        writeToOutput(socketWriter, HttpResponseCode.OK, content);
    }

    public void writeBadRequestResponse(BufferedWriter socketWriter) throws IOException {
        writeToOutput(socketWriter, HttpResponseCode.BAD_REQUEST, null);
    }

    public void writeNotFoundResponse(BufferedWriter socketWriter) throws IOException {
        writeToOutput(socketWriter, HttpResponseCode.NOT_FOUND, null);
    }

    public void writeInternalServerResponse(BufferedWriter socketWriter) throws IOException {
        writeToOutput(socketWriter, HttpResponseCode.INTERNAL_SERVER_ERROR, null);
    }

    private void writeToOutput(BufferedWriter socketWriter, HttpResponseCode httpResponseCode, String content) throws IOException {
        socketWriter.write(httpResponseCode.getMessage());
        socketWriter.write(EOL);
        if (content != null) {
            socketWriter.write(content);
        }
        socketWriter.flush();
    }
}
