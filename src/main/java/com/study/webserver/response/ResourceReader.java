package com.study.webserver.response;

import com.study.webserver.exception.NotFoundResponse;
import com.study.webserver.request.Request;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ResourceReader {

    public String getResource(Request request, String webAppPath) throws NotFoundResponse {
        String fileContent;
        String filePath = webAppPath + request.getUrl();
        System.out.println(filePath);
        File file = new File(filePath);
        System.out.println(file.getAbsolutePath());
        if (!file.exists() || file.isDirectory()) {
            throw new NotFoundResponse("Requested path doesn't exist on server");
        }
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file));) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = fileReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            fileContent = stringBuilder.toString();
        } catch (Exception e) {
            fileContent = "Unknown location, please check logs";
            e.printStackTrace();
        }

        //System.out.println(fileContent);
        return fileContent;
    }
}
