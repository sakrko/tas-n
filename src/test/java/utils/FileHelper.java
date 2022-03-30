package utils;

import org.testng.Assert;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


public class FileHelper {
    public static String getFileToString(String dir, String fileName) {
        String str = null;
        String basePath = null;
        switch (dir) {
            case "body":
                basePath = "/src/test/resources/data/body/";
                break;
            case "responseBody":
                basePath = "/src/test/resources/data/responseBody/";
                break;
            default:
                Assert.fail("No such base path is found");
                break;
        }
        try {
            str = Files.readString(Path.of(System.getProperty("user.dir") + basePath + fileName), StandardCharsets.US_ASCII);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getScenarioPath(String schemaFileName) {
        return System.getProperty("user.dir") + "/src/test/resources/data/schemas/" + schemaFileName;
    }
}