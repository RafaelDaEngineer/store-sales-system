package se.kth.iv1350.storesalessystem.startup;

import se.kth.iv1350.storesalessystem.controller.Controller;
import se.kth.iv1350.storesalessystem.integration.AccountingSystem;
import se.kth.iv1350.storesalessystem.integration.DiscountDatabase;
import se.kth.iv1350.storesalessystem.integration.InventorySystem;
import se.kth.iv1350.storesalessystem.integration.Printer;
import se.kth.iv1350.storesalessystem.view.View;

public class Main {
    public static void main(String[] args) {
        // Create the systems
        InventorySystem inventorySystem = new InventorySystem();
        DiscountDatabase discountDatabase = new DiscountDatabase();
        AccountingSystem accountingSystem = new AccountingSystem();
        Printer printer = new Printer();
        
        // Create the controller
        Controller controller = new Controller(
            inventorySystem, 
            discountDatabase, 
            accountingSystem, 
            printer
        );
        
        // Create the view
        View view = new View(controller);
        
        // The actual testing will be done through JUnit tests
        // which you'll add later
    }
}