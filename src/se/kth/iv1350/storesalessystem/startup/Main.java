package se.kth.iv1350.storesalessystem.startup;

import se.kth.iv1350.storesalessystem.controller.Controller;
import se.kth.iv1350.storesalessystem.integration.AccountingSystem;
import se.kth.iv1350.storesalessystem.integration.DiscountDatabase;
import se.kth.iv1350.storesalessystem.integration.InventorySystem;
import se.kth.iv1350.storesalessystem.integration.Printer;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.Amount;
import se.kth.iv1350.storesalessystem.model.Receipt;
import se.kth.iv1350.storesalessystem.view.View;

public class Main {
    public static void main(String[] args) {
        // Create the systems
        InventorySystem inventorySystem = new InventorySystem();
        DiscountDatabase discountDatabase = new DiscountDatabase();
        AccountingSystem accountingSystem = new AccountingSystem();
        Printer printer = new Printer();

        populateInventory(inventorySystem);

        // Create the controller
        Controller controller = new Controller(
            inventorySystem, 
            discountDatabase, 
            accountingSystem, 
            printer
        );
        
        // Create the view
        View view = new View(controller);

        System.out.println("=== Starting New Sale ===");
        controller.startSale();

        System.out.println("\n=== Scanning Items ===");

        // Scan Wagyu Steak (1 quantity)
        scanItemIndividually(controller, view, "A1", 1);

        // Scan Eggs (2 quantity)
        scanItemIndividually(controller, view, "BBL304", 2);

        // Scan Water (5 quantity)
        scanItemIndividually(controller, view, "H2O", 5);

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
     * Helper method to scan items individually based on quantity
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



    private static void populateInventory(InventorySystem inventory){
        inventory.addItem(new ItemDTO("A1", "Steak", "Japanese Wagyu 250g", 0.25, new Amount(799.0)));
        inventory.addItem(new ItemDTO("BBL304", "Eggs", "12-Pack Eggs", 0.12, new Amount(129.90)));
        inventory.addItem(new ItemDTO("H2O", "Water", "1 Liter", 0.06, new Amount(19.50)));
    }
}