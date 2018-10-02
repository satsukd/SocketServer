package com.study.webserver;

import com.study.webserver.request.RequestHandler;
import com.study.webserver.response.ResponseWriter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private int port;
    private String webAppPath;
    private Boolean isServerRunning = Boolean.FALSE;

    public Server(int port, String webAppPath) {
        this.port = port;
        this.webAppPath = webAppPath;
    }

    public void start() throws IOException {
        validateServerSettings();
        if (isServerRunning) {
            return;
        }
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            isServerRunning = Boolean.TRUE;
            System.out.println("Server has been started");
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader socketReader = new BufferedReader(new InputStreamReader((socket.getInputStream())));
                     BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter((socket.getOutputStream())));) {
                    RequestHandler requestHandler = new RequestHandler(socketReader, socketWriter, webAppPath);
                    try {
                        requestHandler.handle();
                    } catch (Exception e) {
                        e.printStackTrace();
                        ResponseWriter responseWriter = new ResponseWriter();
                        responseWriter.writeInternalServerResponse(socketWriter);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int port = 8080;
        String path = "src\\main\\resources";
        Server server = new Server(port, path);
        server.start();
    }

    private void validateServerSettings() {
        if (port <= 0 || port > 65000) {
            throw new IllegalArgumentException("Port is not valid");
        }
        if (webAppPath == null) {
            throw new IllegalArgumentException("WebAppPath is not valid");
        }
    }
}
