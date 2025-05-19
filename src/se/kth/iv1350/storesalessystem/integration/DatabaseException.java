package se.kth.iv1350.storesalessystem.integration;


import se.kth.iv1350.storesalessystem.util.ErrorLogger;

/**
 * Represents an exception that occurs during database operations. This class encapsulates
 * information about the failed operation and provides a user-friendly message for error handling.
 */
public class DatabaseException extends Exception {
    private final String userFriendlyMessage;
    private final String operation;
    private final ErrorLogger logger;

    /**
     * Creates a new instance of DatabaseException.
     * This exception is thrown to indicate a failure in performing a specified database operation,
     * typically due to the unavailability of the database server.
     *
     * @param operation The name or description of the database operation that failed.
     *                  This information is used to provide more detailed context
     *                  about the cause of the exception.
     */
    public DatabaseException(String operation) {
        super("Database operation failed: " + operation + " - Database server is unavailable");
        this.operation = operation;
        this.userFriendlyMessage = "The system is temporarily unavailable. Please try again later or contact assistance";
        this.logger = new ErrorLogger();
    }

    /**
     * Logs an error message with contextual information about a failed database operation.
     * This method captures the details of the operation that failed and documents the error
     * in a log for further analysis. The logged information includes an exception and
     * a message explaining the nature of the failure.
     */
    public void logError() {
        logger.logException(new Exception(), "Database operation failed: " + operation + " - Database server is unavailable");
    }

    /**
     * Retrieves the user-friendly error message associated with this exception.
     * This message is intended to provide a clear and understandable explanation
     * of the issue to the end-user, for example, in UI error dialogs or logs.
     *
     * @return A string containing the user-friendly error message.
     */
    public String getUserFriendlyMessage() {
        return userFriendlyMessage;
    }

    /**
     * Retrieves the description of the database operation that failed.
     * This information provides context about the specific operation
     * that triggered the exception.
     *
     * @return A string representing the name or description of the failed database operation.
     */
    public String getOperation() {
        return operation;
    }
}