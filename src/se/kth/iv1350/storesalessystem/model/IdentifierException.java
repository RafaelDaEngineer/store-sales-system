package se.kth.iv1350.storesalessystem.model;

import se.kth.iv1350.storesalessystem.util.ErrorLogger;

/**
 * Represents an exception that is thrown when an item with a specific identifier cannot be found.
 * This exception includes the identifier of the missing item and a user-friendly message
 * to provide context for the error.
 */
public class IdentifierException extends Exception {
    private final String itemIdentifier;
    private final String userFriendlyMessage;
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
        this.userFriendlyMessage = "The scanned item could not be found in the inventory. Please try again or contact assistance";
        this.logger = new ErrorLogger();
    }

    public void logError() {
        logger.logException(exception, "Item with ID: " + itemIdentifier + " not found in the inventory.");
    }

    public String getItemIdentifier() {
        return itemIdentifier;
    }

    public String getUserFriendlyMessage() {
        return userFriendlyMessage;
    }
}
