package com.study.webserver.request;

import com.study.webserver.TestConfigurator;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class RequestHandlerTest {

    @Test
    public void handle() throws IOException {

        InputStream inputStream = new ByteArrayInputStream(TestConfigurator.TEST_REQUEST.getContent().getBytes());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(byteArrayOutputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        RequestHandler requestHandler = new RequestHandler(bufferedReader, bufferedWriter, TestConfigurator.WEB_APP_PATH.getContent());
        requestHandler.handle();

        assertEquals(getTestFileSize(), byteArrayOutputStream.size());
    }

    private int getTestFileSize() throws IOException {
        int size = TestConfigurator.ON_SUCCESS_HEADER.getContent().getBytes().length;

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