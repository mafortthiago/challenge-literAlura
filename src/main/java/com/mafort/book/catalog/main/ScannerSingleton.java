package com.mafort.book.catalog.main;

import java.io.InputStream;
import java.util.Scanner;

public class ScannerSingleton {
    private static Scanner scanner;

    private ScannerSingleton() {
    }

    private static void createScanner(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    public static Scanner getScanner(InputStream inputStream) {
        if (scanner == null) {
            createScanner(inputStream);
        }
        return scanner;
    }

    public static void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
        scanner = null;
    }
}
