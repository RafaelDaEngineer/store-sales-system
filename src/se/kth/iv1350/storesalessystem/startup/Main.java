package se.kth.iv1350.storesalessystem.startup;

import se.kth.iv1350.storesalessystem.controller.Controller;
import se.kth.iv1350.storesalessystem.integration.*;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.Amount;
import se.kth.iv1350.storesalessystem.model.IdentifierException;
import se.kth.iv1350.storesalessystem.util.ErrorLogger;
import se.kth.iv1350.storesalessystem.view.View;

public class Main {
    public static void main(String[] args) {
        InventorySystem inventorySystem = new InventorySystem();
        AccountingSystem accountingSystem = new AccountingSystem();
        Printer printer = new Printer();

        populateInventory(inventorySystem);

        Controller controller = new Controller(inventorySystem, accountingSystem, printer);

        View view = new View(controller);

        System.out.println("=== Starting New Sale ===");
        controller.startSale();

        System.out.println("\n=== Scanning Items ===");

        scanItemIndividually(controller, view, "A1", 1);
        scanItemIndividually(controller, view, "ABC123", 2);
        scanItemIndividually(controller, view, "H2O", 1);

        System.out.println("\n=== Before Discount ===");
        Amount beforeDiscount = controller.getCurrentTotal();
        System.out.println("Total before discount: " + beforeDiscount.getAmount() + " SEK");

        System.out.println("\n=== Applying Discount ===");
        int customerID = 25000;
        controller.requestDiscount(customerID);

        System.out.println("\n=== After Discount ===");
        Amount originalTotal = controller.getOriginalTotal();
        Amount afterDiscount = controller.getCurrentTotal();
        String discountDescription = controller.getDiscountDescription();
        view.displayCurrentTotal(afterDiscount, discountDescription, originalTotal);

        System.out.println("\n=== Error Handling Demo ===");
        scanItemIndividually(controller, view, "NONEXISTENT", 1);
        scanItemIndividually(controller, view, "DB-ERROR-999", 1);

        System.out.println("\n=== End Sale ===");
        Amount totalPrice = controller.endSale();
        System.out.println("Final price (incl VAT): " + totalPrice.getAmount() + " SEK");
        System.out.println("Discount applied: " + controller.getDiscountDescription());

        System.out.println("\n=== Process Payment ===");
        Amount amountPaid = new Amount(1500.00);
        System.out.println("Amount paid: " + amountPaid.getAmount() + " SEK");

        try {
            Amount change = controller.makePayment(amountPaid);
            System.out.println("\n=== Change to be given to the customer ===");
            view.displayChange(change);
        } catch (DatabaseException e) {
            System.out.println("ERROR: " + e.getUserFriendlyMessage());
            System.err.println("Technical details: " + e.getMessage());
        }


    }

    /**
     * Repeatedly scans an item based on the provided quantity and updates the sale process.
     * For each scanned item, it retrieves the item details, displays them, updates
     * the current total, and shows the VAT and other relevant sale information.
     *
     * @param controller The {@link Controller} instance responsible for managing the sale process and item entry.
     * @param view       The {@link View} instance used to display item and sale information to the user.
     * @param itemID     The unique identifier of the item to be scanned.
     * @param quantity   The number of times the item should be scanned.
     */
    private static void scanItemIndividually(Controller controller, View view, String itemID, int quantity) {
        try {
            ItemDTO item = controller.enterItem(itemID, quantity);
            view.displayItem(item);

            String discountDescription = controller.getDiscountDescription();
            if (!discountDescription.equals("No discount")) {
                Amount originalTotal = controller.getOriginalTotal();
                Amount afterDiscount = controller.getCurrentTotal();
                view.displayCurrentTotal(afterDiscount, discountDescription, originalTotal);
            } else {
                view.displayCurrentTotal(controller.getCurrentTotal());
            }

            view.displayTotalVAT();
            System.out.println("------------------------------------------\n");
        } catch (IdentifierException e) {
            System.out.println("ERROR: " + e.getUserFriendlyMessage());
            System.err.println("Technical details: " + e.getMessage());
            e.logError();
        } catch (DatabaseException e) {
            System.out.println("ERROR: " + e.getUserFriendlyMessage());
            System.err.println("Technical details: " + e.getMessage());
            e.logError();
        }
    }


    /**
     * Populates the given inventory system with a predefined set of items.
     * This method adds a selection of items, each represented by an {@code ItemDTO},
     * into the inventory system by using its {@code addItem} method.
     *
     * @param inventory The {@code InventorySystem} instance into which the predefined
     *                  items will be added. This instance will store the added items
     *                  and allow their retrieval and management.
     */
    private static void populateInventory(InventorySystem inventory) {
        inventory.addItem(new ItemDTO("A1", "Steak", "Japanese Wagyu 250g", 0.25, new Amount(799.0)));
        inventory.addItem(new ItemDTO("ABC123", "Eggs", "12-Pack Eggs", 0.12, new Amount(129.90)));
        inventory.addItem(new ItemDTO("H2O", "Water", "1 Liter", 0.06, new Amount(19.50)));
    }
}