package se.kth.iv1350.storesalessystem.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.Amount;
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
    void testGetItemInfoExistingItem(){
        ItemDTO result = inventorySystem.getItemInfo("1");
        assertNotNull(result, "Should find item with ID 1");
        assertEquals("Test Item", result.getName(), "Item name should match");
        assertEquals(100.0, result.getPrice().getAmount(), "Item price should match");
    }

    @Test
    void testGetItemInfoNonExistingItem(){
        ItemDTO result = inventorySystem.getItemInfo("999");
        assertNull(result, "Should return null for non-existing item");
    }

    @Test
    void testAddItem(){
        ItemDTO newItem = new ItemDTO("2", "New Item", "New Description", 0.12, new Amount(50));
        inventorySystem.addItem(newItem);

        ItemDTO result = inventorySystem.getItemInfo("2");
        assertNotNull(result, "Should find newly added item with ID 2");
        assertEquals("New Item", result.getName(), "Item name should match");
    }

    @Test
    void testUpdateInventory(){
        // This method is empty in the implementation, but we'll test that it doesn't throw exceptions
        SaleInfoDTO saleInfo = new SaleInfoDTO(
                1, new Amount(100), Collections.singletonList(testItem), 0, new Amount(25)
        );

        assertDoesNotThrow(() -> inventorySystem.updateInventory(saleInfo),
                "Update inventory should not throw exceptions");
    }
}