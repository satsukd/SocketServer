package com.study.webserver.response;

import com.study.webserver.TestConfigurator;
import com.study.webserver.exception.NotFoundResponse;
import com.study.webserver.request.Request;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

public class ResourceReaderTest {
    private Request request;
    private ResourceReader resourceReader;

    @Before
    public void setUp() {
        request = new Request();
        request.setUrl(TestConfigurator.RESOURCE_NAME.getContent());
        resourceReader = new ResourceReader();
    }

    @Test
    public void getResource() throws NotFoundResponse, IOException {
        String content = resourceReader.getResource(request, TestConfigurator.WEB_APP_PATH.getContent());
        assertEquals(getTestFileSize(), content.length());

    }

    private int getTestFileSize() throws IOException {
        int size = 0;
        File file = new File(TestConfigurator.WEB_APP_PATH.getContent() + TestConfigurator.RESOURCE_NAME.getContent());

        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = fileReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            size += stringBuilder.toString().getBytes().length;
        }

        return size;
    }
}