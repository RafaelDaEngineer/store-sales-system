package se.kth.iv1350.storesalessystem.model;

import se.kth.iv1350.storesalessystem.integration.AccountingSystem;
import se.kth.iv1350.storesalessystem.integration.InventorySystem;
import se.kth.iv1350.storesalessystem.model.dto.SaleInfoDTO;

/**
 * The SaleLogger class is responsible for logging completed sales by updating
 * both the accounting system and the inventory system. It acts as a mediator
 * for recording sale information in these external systems after a sale is completed.
 */
public class SaleLogger {
    private final AccountingSystem accountingSystem;
    private final InventorySystem inventorySystem;

    /**
     * Constructs an instance of SaleLogger to facilitate the logging of completed sales.
     * It updates both the accounting system and the inventory system with the relevant
     * details from the completed sale.
     *
     * @param accountingSystem The accounting system that receives updates about completed sales.
     * @param inventorySystem The inventory system that is updated with information about sold items.
     */
    public SaleLogger(AccountingSystem accountingSystem, InventorySystem inventorySystem){
        this.accountingSystem = accountingSystem;
        this.inventorySystem = inventorySystem;
    }

    /**
     * Logs a completed sale by updating both the inventory system and the accounting system
     * with the details of the sale.
     *
     * @param sale The completed sale to be logged, containing all relevant sale information.
     */
    public void logCompletedSale(Sale sale){
        SaleInfoDTO saleInfo = sale.getSaleInfo();
        inventorySystem.updateInventory(saleInfo);
        accountingSystem.updateAccounting(saleInfo);
    }
}
