package com.homework.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class SqlFileLoader {

    private SqlFileLoader() {
    }

    public static String load(String relativePath) {
        try {
            return Files.readString(Path.of(relativePath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load SQL file: " + relativePath, e);
        }
    }
}
