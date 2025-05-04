package se.kth.iv1350.storesalessystem.controller;

import se.kth.iv1350.storesalessystem.integration.AccountingSystem;
import se.kth.iv1350.storesalessystem.integration.DiscountDatabase;
import se.kth.iv1350.storesalessystem.integration.InventorySystem;
import se.kth.iv1350.storesalessystem.integration.Printer;
import se.kth.iv1350.storesalessystem.integration.dto.DiscountInfoDTO;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.*;


public class Controller {
    private InventorySystem inventorySystem;
    private DiscountDatabase discountDatabase;
    private AccountingSystem accountingSystem;
    private CashRegister cashRegister;
    private ReceiptPrinter receiptPrinter;
    private SaleLogger saleLogger;
    private Sale currentSale;


    public Controller(InventorySystem inventorySystem, DiscountDatabase discountDatabase, AccountingSystem accountingSystem, Printer printer){
        this.inventorySystem = inventorySystem;
        this.discountDatabase = discountDatabase;
        this.accountingSystem = accountingSystem;
        this.cashRegister = new CashRegister();
        this.receiptPrinter = new ReceiptPrinter(printer);
        this.saleLogger = new SaleLogger(accountingSystem, inventorySystem);
    }

    public void startSale() {
        int saleID = generateUniqueID();
        currentSale = new Sale(saleID);
    }

    private int generateUniqueID() {
        return (int) (System.currentTimeMillis() % 100000);
    }

    /**
     * Enters an item into the current sale.
     *
     * @param itemID The ID of the specified item.
     * @param quantity The quantity of the specified item.
     * @return The item information if found, null if not found.
     */
    public ItemDTO enterItem(String itemID, int quantity) {
        ItemDTO itemInfo = inventorySystem.getItemInfo(itemID);

        if (itemInfo == null) {
            return null;
        }

        SaleItem existingItem = currentSale.findItemByID(itemID);
        if (existingItem != null) {
            currentSale.increaseItemQuantity(itemID, quantity);
        } else {
            currentSale.addItem(itemInfo, quantity);
        }

        return itemInfo;
    }


    public void requestDiscount(int customerID) {
        currentSale.setCustomerID(customerID);
        DiscountInfoDTO discountInfo = discountDatabase.findDiscount(customerID, currentSale.getSaleInfo());
        currentSale.applySaleDiscount(discountInfo);
    }

    public Amount endSale(){
        return currentSale.getTotalAfterDiscount();
    }

    /**
     * Makes payment for the current sale.
     * @param paidAmount The amount paid by the customer
     * @return The change to give to the customer
     */
    public Amount makePayment(Amount paidAmount) {
        // Calculate change
        Amount total = currentSale.getTotalAfterDiscount();
        Amount change = paidAmount.minus(total);

        // Update cash register
        cashRegister.addPayment(total);

        // Update external systems
        saleLogger.logCompletedSale(currentSale);

        // Print receipt
        receiptPrinter.printReceipt(currentSale, paidAmount);

        return change;
    }

    public Amount getTotalVAT() {
        return currentSale.getTotalVAT();
    }

    public Amount getCurrentTotal() {
        return currentSale.getTotalAfterDiscount();
    }

}
