package se.kth.iv1350.storesalessystem.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorLogger implements Logger {
    private static final String LOG_FILE_NAME = "errorlog.txt";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private PrintWriter logStream;

    public ErrorLogger() {
        try {
            logStream = new PrintWriter(new FileWriter(LOG_FILE_NAME, true));
        } catch (IOException e) {
            System.err.println("ERROR: Could not create or open the log file: " + e.getMessage());
        }
    }

    public void logException(Exception exception, String contextInfo) {
        if (logStream == null) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(TIME_FORMATTER);

        logStream.println();
        logStream.println("\n-----------------------ERROR LOG---------------------");
        logStream.println("Timestamp: " + timestamp);
        logStream.println("Context: " + contextInfo);
        logStream.println("Exception Type: " + exception.getClass().getName());
        logStream.println("Exception Message: " + exception.getMessage());
        logStream.println("------------------------------------------------");
        logStream.println("Stack trace:");
        exception.printStackTrace(logStream);
        logStream.println("------------------------------------------------\n");
    }
}
