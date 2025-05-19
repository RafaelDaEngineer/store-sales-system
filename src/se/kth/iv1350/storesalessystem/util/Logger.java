package se.kth.iv1350.storesalessystem.util;

/**
 * Represents a logging mechanism for recording exceptions and their associated context information.
 * This interface provides a contract for logging exception details, which can include a message,
 * stack trace, and additional context about where the exception occurred.
 */
public interface Logger {

    /**
     * Logs details of an exception, including context information and the exception's details,
     * to provide a record of errors for analysis and troubleshooting.
     *
     * @param exception   The exception to log, containing information such as its type,
     *                    message, and stack trace.
     * @param contextInfo A string describing the context in which the exception occurred,
     *                    offering additional details to aid in understanding the error.
     */
    void logException(Exception exception, String contextInfo);
}
