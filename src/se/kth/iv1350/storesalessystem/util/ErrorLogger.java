package se.kth.iv1350.storesalessystem.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles logging of error messages and exceptions into a dedicated error log file.
 * This class implements the {@link Logger} interface to provide error-specific logging functionality.
 * Logged entries include a timestamp, context information, exception details, and stack trace.
 */
public class ErrorLogger implements Logger {
    private static final String LOG_FILE_NAME = "errorlog.txt";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    /**
     * The output stream used to write error logs to a file.
     * It provides the ability to append logged error messages,
     * timestamps, and exception details to the designated log file.
     * If the file cannot be created or opened, this stream will remain uninitialized.
     */
    private PrintWriter logStream;

    /**
     * Initializes a new instance of the {@code ErrorLogger} class.
     * This constructor attempts to create or open a log file for error logging in append mode.
     * If the log file cannot be created or opened, an error message is printed to the standard error stream.
     */
    public ErrorLogger() {
        try {
            logStream = new PrintWriter(new FileWriter(LOG_FILE_NAME, true));
        } catch (IOException e) {
            System.err.println("ERROR: Could not create or open the log file: " + e.getMessage());
        }
    }

    /**
     * Logs details of an exception, including a timestamp, context information,
     * exception type, message, and stack trace, into an error log file.
     * This method ensures that errors are documented for later analysis.
     *
     * @param exception   The exception to log. This includes details such as the type of exception,
     *                    the error message, and the stack trace.
     * @param contextInfo Contextual information where the exception occurred, providing additional
     *                    details to help identify and understand the source of the error.
     */
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
