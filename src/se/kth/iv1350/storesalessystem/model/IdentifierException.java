package se.kth.iv1350.storesalessystem.model;

import se.kth.iv1350.storesalessystem.util.ErrorLogger;

/**
 * Represents an exception that is thrown when an item with a specific identifier cannot be found.
 * This exception includes the identifier of the missing item and a user-friendly message
 * to provide context for the error.
 */
public class IdentifierException extends Exception {
    private final String itemIdentifier;
    private final ErrorLogger logger;
    private final Exception exception = new Exception();

    /**
     * Creates a new instance of the {@code IdentifierException} class to indicate that
     * an item with the specified identifier was not found.
     * This exception provides both a detailed error message and a user-friendly message.
     *
     * @param itemIdentifier The identifier of the item that could not be found.
     */
    public IdentifierException(String itemIdentifier) {
        super("Item with identifier " + itemIdentifier + " was not found");
        this.itemIdentifier = itemIdentifier;
        this.logger = new ErrorLogger();
    }

    /**
     * Logs the details of an exception indicating that an item with a specific identifier
     * was not found in the inventory. This method is used to capture error information
     * for debugging and analysis purposes.
     * The logged information includes:
     * - A description of the error, including the missing item's identifier.
     * - The type and details of the underlying exception.
     * This method utilizes the {@code logException} method of the {@code ErrorLogger} class to
     * write the error details to an error log.
     */
    public void logError() {
        logger.logException(exception, "Item with ID: " + itemIdentifier + " not found in the inventory.");
    }

    /**
     * Retrieves the identifier of the item associated with this exception.
     *
     * @return The identifier of the item that could not be found.
     */
    public String getItemIdentifier() {
        return itemIdentifier;
    }
}
