package se.kth.iv1350.storesalessystem.view;

import se.kth.iv1350.storesalessystem.controller.Controller;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.Amount;

/**
 * Represents the user interface of the application.
 */
public class View {
    private final Controller controller;

    /**
     * Creates a new view.
     *
     * @param controller The controller this view uses for operations.
     */
    public View(Controller controller){
        this.controller = controller;
    }

    /**
     * Displays information about an item.
     *
     * @param itemInfo The item to display.
     */
    public void displayItem(ItemDTO itemInfo){
        System.out.println("Item: " + itemInfo.getDescription());
        System.out.println("Price: " + itemInfo.getPrice().getAmount() + " kr");
        System.out.println("Tax rate: " + (itemInfo.getTax() * 100) + "%");
    }

    /**
     * Displays an error message for a specific error type.
     *
     * @param errorType The type of error that occured.
     * @param errorMessage A descriptive message of the error.
     */
    public void displayError(String errorType, String errorMessage){
        System.out.println("ERROR: " + errorType);
        System.out.println("Message: " + errorMessage);
    }

    /**
     * Displays specific item not found error.
     * @param itemID the ID of the item that was not found.
     */
    public void displayItemNotFoundError(int itemID){
        displayError("Item not found", "Item with ID " + itemID + " was not found.");
    }

    public void displayDatabaseError(String systemName){
        displayError("Database Connection Failed", "Could not connect to " + systemName + " Please try again later.");
    }

    /**
     * Displays the change to be given to the customer.
     *
     * @param total The change amount.
     */
    public void displayCurrentTotal(Amount total){
        System.out.println("Current total: " + total.getAmount() + " kr");
    }

    /**
     * Displays the change to be given to the customer.
     *
     * @param change The change amount.
     */
    public void displayChange(Amount change){
        System.out.println("Change: " + change.getAmount() + " kr");
    }
}
