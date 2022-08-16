package com.linesteams.linesapiserver.lines;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class MockFileTest {

    public static final String FILE_NAME = "sampleImage";
    public static final String CONTENT_TYPE = "png";
    public static final String PATH = "src/test/resources/sampleImage.png";

    @DisplayName("MockMultipartFile 동작 테스트")
    @Test
    void getMockExcelUploadTest() throws IOException {
        MockMultipartFile mockMultipartFile = getMockMultipartFile(FILE_NAME, CONTENT_TYPE, PATH);

        String getFileName = mockMultipartFile.getOriginalFilename().toLowerCase();

        assertThat(getFileName).isEqualTo(FILE_NAME.toLowerCase() + "." + CONTENT_TYPE);
    }

    public static MockMultipartFile getMockMultipartFile() {
        try {
            return getMockMultipartFile(FILE_NAME, CONTENT_TYPE, PATH);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static MockMultipartFile getMockMultipartFile(String fileName, String contentType, String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        return new MockMultipartFile(fileName, fileName + "." + contentType, contentType, fileInputStream);
    }
}
