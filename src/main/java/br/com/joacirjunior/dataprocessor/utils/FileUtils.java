package br.com.joacirjunior.dataprocessor.utils;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;

public class FileUtils {

    public static void createDirectoryIfNotExists(String path) throws IOException {
        File input = new File(path);
        if (!input.exists()) {
            input.mkdirs();
        }
    }

    public static void createDirectoryIfNotExists(String path, String absolutePath) throws IOException {
        File input = new File(pathToLoad(path, absolutePath));
        if (!input.exists()) {
            input.mkdirs();
        }
    }

    public static String pathToLoad(String relativePath, String absolutePath) {
        return MessageFormat.format(relativePath, System.getProperty(absolutePath));
    }

}
