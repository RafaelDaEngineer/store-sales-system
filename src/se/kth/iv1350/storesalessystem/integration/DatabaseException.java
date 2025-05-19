package se.kth.iv1350.storesalessystem.integration;


/**
 * Represents an exception that occurs during database operations. This class encapsulates
 * information about the failed operation and provides a user-friendly message for error handling.
 */
public class DatabaseException extends Exception {
    private final String userFriendlyMessage;
    private final String operation;

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
    }

    public String getUserFriendlyMessage() {
        return userFriendlyMessage;
    }

    public String getOperation() {
        return operation;
    }
}