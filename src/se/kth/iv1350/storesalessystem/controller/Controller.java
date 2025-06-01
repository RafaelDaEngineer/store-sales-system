package se.kth.iv1350.storesalessystem.controller;

import se.kth.iv1350.storesalessystem.integration.*;
import se.kth.iv1350.storesalessystem.integration.dto.DiscountInfoDTO;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.*;
import se.kth.iv1350.storesalessystem.view.TotalRevenueFileOutput;
import se.kth.iv1350.storesalessystem.view.TotalRevenueView;

/**
 * The Controller manages the main interactions between the external systems, sale process,
 * and overall coordination within the application.
 * It handles the execution of a sale, including item addition, discount requests,
 * payment processing, and interaction with external systems such as inventory and accounting.
 */
public class Controller {
    private final InventorySystem inventorySystem;
    private final DiscountDatabase discountDatabase;
    private final CashRegister cashRegister;
    private final ReceiptPrinter receiptPrinter;
    private final SaleLogger saleLogger;
    private Sale currentSale;

    /**
     * Creates a new instance of the Controller class, which coordinates actions
     * between the sale process, inventory system, discount database, accounting system,
     * and receipt printing.
     *
     * @param inventorySystem  The system handling the store's inventory.
     * @param accountingSystem The system managing accounting and financial records.
     * @param printer          The printer used to print sale receipts.
     *                         <p>
     *                         Note: The discount database is accessed through its singleton instance.
     */
    public Controller(InventorySystem inventorySystem, AccountingSystem accountingSystem, Printer printer) {
        this.inventorySystem = inventorySystem;
        this.discountDatabase = DiscountDatabase.getInstance();
        this.cashRegister = new CashRegister();
        this.receiptPrinter = new ReceiptPrinter(printer);
        this.saleLogger = new SaleLogger(accountingSystem, inventorySystem);

        cashRegister.addObserver(new TotalRevenueView());
        cashRegister.addObserver(new TotalRevenueFileOutput());
    }

    /**
     * Initiates a new sale process by creating a new {@link Sale} instance and
     * assigning it a unique sale ID.
     * This method generates a unique identifier for the sale and initializes the
     * {@code currentSale} field, which tracks the ongoing sale.
     * The {@link Sale} instance created will be used throughout the sale process
     * to manage items, calculate totals, apply discounts, and handle payment.
     */
    public void startSale() {
        int saleID = generateUniqueID();
        currentSale = new Sale(saleID);
    }

    /**
     * Generates a unique identifier by using the current system time
     * in milliseconds and applying a modulo operation to constrain the result.
     *
     * @return A unique identifier as an integer, generated based on the current system time.
     */
    private int generateUniqueID() {
        return (int) (System.currentTimeMillis() % 100000);
    }

    /**
     * Adds or updates an item in the current sale based on the specified item ID and quantity.
     * If the item already exists in the current sale, its quantity is increased.
     * If the item does not exist, it is added with the given quantity.
     *
     * @param itemID   The unique identifier of the item to be added or updated in the current sale.
     * @param quantity The number of units of the item to add or update.
     * @return An {@code ItemDTO} object containing detailed information about the item.
     * @throws IdentifierException If the specified item ID is not found in the inventory system.
     * @throws DatabaseException   If there is an issue accessing the inventory system or database.
     */
    public ItemDTO enterItem(String itemID, int quantity) throws IdentifierException, DatabaseException {
        ItemDTO itemInfo = inventorySystem.getItemInfo(itemID);

        try {
            currentSale.findItemByID(itemID);
            currentSale.increaseItemQuantity(itemID, quantity);
        } catch (IdentifierException e) {
            currentSale.addItem(itemInfo, quantity);
        }

        return itemInfo;
    }

    /**
     * Requests a discount for the current sale process based on the provided customer ID.
     * This method retrieves discount information applicable to the specified customer and
     * applies it to the active sale in progress.
     *
     * @param customerID The unique identifier of the customer for whom the discount is requested.
     */
    public void requestDiscount(int customerID) {
        currentSale.setCustomerID(customerID);
        DiscountInfoDTO discountInfo = discountDatabase.findDiscount(customerID, currentSale.getSaleInfo());
        currentSale.applySaleDiscount(discountInfo);
    }


    /**
     * Processes the payment for the current sale by calculating the change,
     * updating the cash register, logging the sale, and printing the receipt.
     *
     * @param paidAmount The amount paid by the customer for the current sale.
     * @return The change to be returned to the customer as an {@code Amount}.
     * @throws DatabaseException If there is an issue accessing the database
     *                           during the sale logging or inventory update process.
     */
    public Amount makePayment(Amount paidAmount) throws DatabaseException {

        Amount total = currentSale.getTotalAfterDiscount();
        Amount change = paidAmount.minus(total);


        cashRegister.addPayment(total);


        saleLogger.logCompletedSale(currentSale);


        receiptPrinter.printReceipt(currentSale, paidAmount);

        return change;
    }

    /**
     * Retrieves the total VAT amount for the current sale.
     *
     * @return The total VAT amount as an {@code Amount}.
     */
    public Amount getTotalVAT() {
        return currentSale.getTotalVAT();
    }

    /**
     * Retrieves the current total amount for the ongoing sale after any applied discount.
     *
     * @return The current total amount as an {@code Amount}.
     */
    public Amount getCurrentTotal() {
        return currentSale.getTotalAfterDiscount();
    }


    /**
     * Finalizes the current sale and retrieves the total amount after applying any discounts.
     * This method marks the end of the sale by calculating and returning the total cost,
     * including any adjustments made by applied discounts.
     *
     * @return The total amount payable for the current sale after discounts, as an {@code Amount}.
     */
    public Amount endSale() {
        return currentSale.getTotalAfterDiscount();
    }

    /**
     * Gets the description of the current discount applied to the sale.
     *
     * @return A string describing the current discount.
     */
    public String getDiscountDescription() {
        return currentSale.getDiscountDescription();
    }

    /**
     * Gets the original total amount before applying any discounts.
     *
     * @return The original total as an Amount object.
     */
    public Amount getOriginalTotal() {
        return currentSale.getRunningTotal();
    }
}
