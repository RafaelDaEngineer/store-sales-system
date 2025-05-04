package se.kth.iv1350.storesalessystem.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.storesalessystem.integration.AccountingSystem;
import se.kth.iv1350.storesalessystem.integration.DiscountDatabase;
import se.kth.iv1350.storesalessystem.integration.InventorySystem;
import se.kth.iv1350.storesalessystem.integration.Printer;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.Amount;

import static org.junit.jupiter.api.Assertions.*;


class ControllerTest {
    private Controller controller;

    @BeforeEach
    public void setUp() {
        InventorySystem inventorySystem = new InventorySystem();
        DiscountDatabase discountDatabase = new DiscountDatabase();
        AccountingSystem accountingSystem = new AccountingSystem();
        Printer printer = new Printer();
        controller = new Controller(inventorySystem, discountDatabase, accountingSystem, printer);

        ItemDTO testItem = new ItemDTO("1", "Test Item", "Test Description", 0.25, new Amount(100));
        inventorySystem.addItem(testItem);
    }

    @Test
    void testStartSale(){
        controller.startSale();
        Amount total = controller.getCurrentTotal();
        assertNotNull(total, "Total should not be null after starting a sale");
        assertEquals(0, total.getAmount(), "Total should be 0 after starting a sale");
    }

    @Test
    void testEnterItemSuccess(){
        controller.startSale();
        ItemDTO result = controller.enterItem("1", 1);
        assertNotNull(result, "Item should be found in inventory");
        assertEquals("Test Item", result.getName(), "Item name should match");

        Amount actualTotal = controller.getCurrentTotal();

        Amount expectedTotal = new Amount(125); // 100 + 25% VAT
        assertEquals(expectedTotal.getAmount(), actualTotal.getAmount(), "Total should include item price with VAT");
    }

    @Test
    void testEnterItemNotFound(){
        controller.startSale();
        ItemDTO result = controller.enterItem("999", 1);
        assertNull(result, "Item should not be found in inventory");
    }

    @Test
    void testEnterMultipleOfTheSameItem(){
        controller.startSale();
        controller.enterItem("1", 1);
        controller.enterItem("1", 2);

        Amount expectedTotal = new Amount(375); // (100*3) + 25% VAT
        Amount actualTotal = controller.getCurrentTotal();
        assertEquals(expectedTotal.getAmount(), actualTotal.getAmount(), "Total should include price of 3 items with VAT");
    }

    @Test
    void testEnterItemWithQuantity(){
        controller.startSale();
        controller.enterItem("1", 3);

        Amount expectedTotal = new Amount(375); // 100 * 3 + 25% VAT
        Amount actualTotal = controller.getCurrentTotal();
        assertEquals(expectedTotal.getAmount(), actualTotal.getAmount(), "Total should include price of 3 items with VAT");
    }

    @Test
    void testEndSale(){
        controller.startSale();
        controller.enterItem("1", 1);
        Amount total = controller.endSale();

        Amount expectedTotal = new Amount(125);
        assertEquals(expectedTotal.getAmount(), total.getAmount(), "Total should be equal to the total after sale");
    }

    @Test
    void testMakePayment(){
        controller.startSale();
        controller.enterItem("1", 1);
        Amount paidAmount = new Amount(200);
        Amount change = controller.makePayment(paidAmount);

        Amount expectedChange = new Amount(75); // 200 -125
        assertEquals(expectedChange.getAmount(), change.getAmount(), "Change should be calculated correctly");
    }

    @Test
    void testGetTotalWithVAT(){
        controller.startSale();
        controller.enterItem("1", 1);

        Amount expectedTotal = new Amount(25); // 25% of 100
        Amount actualTotal = controller.getTotalVAT();
        assertEquals(expectedTotal.getAmount(), actualTotal.getAmount(), "Total VAT should be calculated correctly");
    }

    @Test
    void testRequestDiscount(){
        // This test is a little more complex because we can't easily mock the DiscountDatabase
        // Instead we'll just verify that the method doesn't throw exceptions
        controller.startSale();
        controller.enterItem("1", 1);

        assertDoesNotThrow( () -> controller.requestDiscount(12345), "Request discount should not throw an exception");
    }
}