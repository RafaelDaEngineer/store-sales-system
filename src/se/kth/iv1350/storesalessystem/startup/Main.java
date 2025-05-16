package se.kth.iv1350.storesalessystem.startup;

import se.kth.iv1350.storesalessystem.controller.Controller;
import se.kth.iv1350.storesalessystem.integration.AccountingSystem;
import se.kth.iv1350.storesalessystem.integration.DiscountDatabase;
import se.kth.iv1350.storesalessystem.integration.InventorySystem;
import se.kth.iv1350.storesalessystem.integration.Printer;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.Amount;
import se.kth.iv1350.storesalessystem.view.View;

public class Main {
    public static void main(String[] args) {
        InventorySystem inventorySystem = new InventorySystem();
        DiscountDatabase discountDatabase = new DiscountDatabase();
        AccountingSystem accountingSystem = new AccountingSystem();
        Printer printer = new Printer();

        populateInventory(inventorySystem);

        Controller controller = new Controller(
                inventorySystem,
                discountDatabase,
                accountingSystem,
                printer
        );

        View view = new View(controller);

        System.out.println("=== Starting New Sale ===");
        controller.startSale();

        System.out.println("\n=== Scanning Items ===");

        scanItemIndividually(controller, view, "A1", 1);

        scanItemIndividually(controller, view, "ABC123a", 2);

        scanItemIndividually(controller, view, "H2O", 1);

        System.out.println("\n=== End Sale ===");
        Amount totalPrice = controller.endSale();
        System.out.println("Total price (incl. VAT): " + totalPrice.getAmount() + " SEK");

        System.out.println("\n=== Process Payment ===");
        Amount amountPaid = new Amount(1500.00);
        System.out.println("Amount paid: " + amountPaid.getAmount() + " SEK");

        // This will automatically print the receipt through the chain:
        // controller -> receiptPrinter -> printer (console output)
        Amount change = controller.makePayment(amountPaid);

        System.out.println("\n=== Change to be given to the customer ===");
        view.displayChange(change);
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
        for (int i = 0; i < quantity; i++) {
            ItemDTO item = controller.enterItem(itemID, 1);
            if (item != null) {
                view.displayItem(item);
                view.displayCurrentTotal(controller.getCurrentTotal());
                view.displayTotalVAT();
                System.out.println("------------------------------------------");
            }
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