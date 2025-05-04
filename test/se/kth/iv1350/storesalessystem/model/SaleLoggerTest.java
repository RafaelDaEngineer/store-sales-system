package se.kth.iv1350.storesalessystem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.storesalessystem.integration.AccountingSystem;
import se.kth.iv1350.storesalessystem.integration.InventorySystem;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

class SaleLoggerTest {
    private SaleLogger saleLogger;
    private Sale sale;
    private AccountingSystem accountingSystem;
    private InventorySystem inventorySystem;

    @BeforeEach
    void setUp() {
        accountingSystem = new AccountingSystem();
        inventorySystem = new InventorySystem();
        saleLogger = new SaleLogger(accountingSystem, inventorySystem);
        sale = new Sale(1);
        ItemDTO item = new ItemDTO("1", "Test Item", "Test Description", 0.25, new Amount(100));
        sale.addItem(item, 2);
    }

    @Test
    void testLogCompletedSale(){
        // Since the actual implementation in AccountingSystem and InventorySystem is empty,
        // we can only test that the method doesn't throw exceptions
        assertDoesNotThrow(() -> saleLogger.logCompletedSale(sale),
                "Logging completed sale should not throw exceptions");
    }

}