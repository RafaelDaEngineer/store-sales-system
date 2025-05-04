package se.kth.iv1350.storesalessystem.model;

import se.kth.iv1350.storesalessystem.integration.AccountingSystem;
import se.kth.iv1350.storesalessystem.integration.InventorySystem;
import se.kth.iv1350.storesalessystem.model.dto.SaleInfoDTO;

public class SaleLogger {
    private AccountingSystem accountingSystem;
    private InventorySystem inventorySystem;

    public SaleLogger(AccountingSystem accountingSystem, InventorySystem inventorySystem){
        this.accountingSystem = accountingSystem;
        this.inventorySystem = inventorySystem;
    }

    public void logCompletedSale(Sale sale){
        SaleInfoDTO saleInfo = sale.getSaleInfo();
        inventorySystem.updateInventory(saleInfo);
        accountingSystem.updateAccounting(saleInfo);
    }
}
