package se.kth.iv1350.storesalessystem.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.storesalessystem.integration.*;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.Amount;
import se.kth.iv1350.storesalessystem.model.IdentifierException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ControllerTest {
    private Controller controller;

    @BeforeEach
    public void setUp() {
        InventorySystem inventorySystem = new InventorySystem();
        AccountingSystem accountingSystem = new AccountingSystem();
        Printer printer = new Printer();

        ItemDTO testItem1 = new ItemDTO("1", "Test Item", "Test Description", 0.25, new Amount(100));
        ItemDTO testItem2 = new ItemDTO("2", "Test Item 2", "Test Description 2", 0.12, new Amount(50));
        inventorySystem.addItem(testItem1);
        inventorySystem.addItem(testItem2);

        controller = new Controller(inventorySystem, accountingSystem, printer);

        controller.startSale();
    }

    @Test
    void testStartSale() {
        controller.startSale();
        Amount total = controller.getCurrentTotal();
        assertNotNull(total, "Total should not be null after starting a sale");
        assertEquals(0, total.getAmount(), "Total should be 0 after starting a sale");
    }

    @Test
    void testEnterItemSuccess() throws IdentifierException, DatabaseException {
        ItemDTO result = controller.enterItem("1", 1);
        assertNotNull(result, "Item should be found in inventory");
        assertEquals("Test Item", result.name(), "Item name should match");

        Amount actualTotal = controller.getCurrentTotal();

        Amount expectedTotal = new Amount(125);
        assertEquals(expectedTotal.getAmount(), actualTotal.getAmount(), "Total should include item price with VAT");
    }

    @Test
    void testEnterItemNotFoundThrowsIdentifierException() throws IOException {

        IdentifierException exception = assertThrows(IdentifierException.class, () -> controller.enterItem("NONEXISTENT", 1), "IdentifierException should be thrown for non-existent item");

        assertEquals("NONEXISTENT", exception.getItemIdentifier(), "Identifier should match item ID");
        assertTrue(exception.getMessage().contains("NONEXISTENT"), "Exception message should contain item ID");
        assertNotNull(exception.getUserFriendlyMessage(), "User friendly message should not be null");
    }

    @Test
    void testEnterItemDatabaseExceptionThrowsDatabaseException() {
        DatabaseException exception = assertThrows(DatabaseException.class, () -> controller.enterItem("DB-ERROR-999", 1));

        assertEquals("getItemInfo", exception.getOperation(), "Operation should match");
        assertNotNull(exception.getUserFriendlyMessage(), "User friendly message should not be null");
    }

    @Test
    void testEnterMultipleOfTheSameItem() throws IdentifierException, DatabaseException {
        controller.enterItem("1", 1);
        controller.enterItem("1", 2);

        Amount expectedTotal = new Amount(375); // (100*3) + 25% VAT
        Amount actualTotal = controller.getCurrentTotal();
        assertEquals(expectedTotal.getAmount(), actualTotal.getAmount(), "Total should include price of 3 items with VAT");
    }

    @Test
    void testEnterItemWithQuantity() throws IdentifierException, DatabaseException {
        controller.enterItem("1", 3);

        Amount expectedTotal = new Amount(375); // 100 * 3 + 25% VAT
        Amount actualTotal = controller.getCurrentTotal();
        assertEquals(expectedTotal.getAmount(), actualTotal.getAmount(), "Total should include price of 3 items with VAT");
    }

    @Test
    void testEndSale() throws IdentifierException, DatabaseException {
        controller.enterItem("1", 1);
        Amount total = controller.endSale();

        Amount expectedTotal = new Amount(125);
        assertEquals(expectedTotal.getAmount(), total.getAmount(), "Total should be equal to the total after sale");
    }

    @Test
    void testMakePayment() throws IdentifierException, DatabaseException {
        controller.startSale();
        controller.enterItem("1", 1);
        Amount paidAmount = new Amount(200);
        Amount change = controller.makePayment(paidAmount);

        Amount expectedChange = new Amount(75); // 200 -125
        assertEquals(expectedChange.getAmount(), change.getAmount(), "Change should be calculated correctly");
    }

    @Test
    void testGetTotalWithVAT() throws IdentifierException, DatabaseException {
        controller.enterItem("1", 1);

        Amount expectedTotal = new Amount(25);
        Amount actualTotal = controller.getTotalVAT();
        assertEquals(expectedTotal.getAmount(), actualTotal.getAmount(), "Total VAT should be calculated correctly");
    }

    @Test
    void testRequestDiscount() throws IdentifierException, DatabaseException {
        controller.enterItem("1", 1);

        assertDoesNotThrow(() -> controller.requestDiscount(12345), "Request discount should not throw an exception");
    }
}