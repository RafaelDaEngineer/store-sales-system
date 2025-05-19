package se.kth.iv1350.storesalessystem.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.Amount;
import se.kth.iv1350.storesalessystem.model.IdentifierException;
import se.kth.iv1350.storesalessystem.model.dto.SaleInfoDTO;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class InventorySystemTest {
    private InventorySystem inventorySystem;
    private ItemDTO testItem;

    @BeforeEach
    void setUp() {
        inventorySystem = new InventorySystem();
        testItem = new ItemDTO("1", "Test Item", "Test Description", 0.25, new Amount(100));
        inventorySystem.addItem(testItem);
    }

    @Test
    void testGetItemInfoExistingItemReturnsCorrectItem() throws IdentifierException, DatabaseException {
        ItemDTO result = inventorySystem.getItemInfo("1");
        assertEquals(testItem.itemID(), result.itemID(), "Item ID should match");
        assertEquals(testItem.name(), result.name(), "Item name should match");
        assertEquals(testItem.price().getAmount(), result.price().getAmount(), "Item price should match");
    }

    @Test
    void testGetItemInfoNonExistingItemThrowsIdentifierException() {
        String nonExistentItemID = "NONEXISTENT";
        IdentifierException exception = assertThrows(IdentifierException.class, () -> inventorySystem.getItemInfo(nonExistentItemID));
        assertEquals(nonExistentItemID, exception.getItemIdentifier(), "Identifier should match item ID");
        assertTrue(exception.getMessage().contains(nonExistentItemID), "Exception message should contain item ID");
        assertEquals("The scanned item could not be found in the inventory. Please try again or contact assistance", exception.getUserFriendlyMessage(), "User friendly message should match");
    }

    @Test
    void testGetItemInfoDatabaseExceptionThrowsDatabaseException() {
        String errorTriggeringID = "DB-ERROR-999";
        DatabaseException exception = assertThrows(DatabaseException.class, () -> inventorySystem.getItemInfo(errorTriggeringID));
        assertEquals("getItemInfo", exception.getOperation(), "Operation should match");
        assertEquals("The system is temporarily unavailable. Please try again later or contact assistance", exception.getUserFriendlyMessage(), "User friendly message should match");
    }

    @Test
    void testAddItem() throws IdentifierException, DatabaseException {
        ItemDTO newItem = new ItemDTO("2", "New Item", "New Description", 0.12, new Amount(50));
        inventorySystem.addItem(newItem);

        ItemDTO result = inventorySystem.getItemInfo("2");
        assertEquals(newItem.itemID(), result.itemID(), "Item ID should match");
        assertEquals(newItem.name(), result.name(), "Name should match");
    }

    @Test
    void testUpdateInventory() {
        // This method is empty in the implementation, but we'll test that it doesn't throw exceptions
        SaleInfoDTO saleInfo = new SaleInfoDTO(
                1, new Amount(100), Collections.singletonList(testItem), 0, new Amount(25)
        );

        assertDoesNotThrow(() -> inventorySystem.updateInventory(saleInfo),
                "Update inventory should not throw exceptions");
    }
}