package se.kth.iv1350.storesalessystem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.storesalessystem.integration.dto.DiscountInfoDTO;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {
    private Sale sale;
    private ItemDTO testItem;
    private ItemDTO anotherTestItem;

    @BeforeEach
    void setUp(){
        sale = new Sale(1);
        testItem = new ItemDTO("1", "Item 1", "Description 1", 0.25, new Amount(100));
        anotherTestItem = new ItemDTO("2", "Item 2", "Description 2", 0.12, new Amount(50));
    }

    @Test
    void testAddItem(){
        sale.addItem(testItem, 1);
        assertEquals(1, sale.getItems().size(), "Sale should contain one item");

        SaleItem saleItem = sale.getItems().get(0);
        assertEquals("1", saleItem.getItemDTO().getItemID(), "Item ID should match");
        assertEquals(1, saleItem.getQuantity(), "Sale should contain correct quantity");
    }

    @Test
    void testUpdateRunningTotal(){
        sale.addItem(testItem, 2); // 2 * 100 = 200
        sale.addItem(anotherTestItem, 3); // 3 * 50 = 150
        // Total: 350

        Amount expectedTotal = new Amount(350 + 68);
        Amount expectedVAT = new Amount((2 * 100 * 0.25) + (3 * 50 * 0.12)); //50 + 18 = 68

        assertEquals(expectedTotal.getAmount(), sale.getSaleInfo().getRunningTotal().getAmount(), "Running total should be correct");
        assertEquals(expectedVAT.getAmount(), sale.getSaleInfo().getTotalVAT().getAmount(), "Total VAT should be correct");
    }

    @Test
    void testFindItemByID() {
        sale.addItem(testItem, 1);
        sale.addItem(anotherTestItem, 2);

        SaleItem foundItem = sale.findItemByID("1");
        assertNotNull(foundItem, "Item should be found");
        assertEquals("Item 1", foundItem.getItemDTO().getName(), "Item name should match");

        SaleItem notFoundItem = sale.findItemByID("999");
        assertNull(notFoundItem, "Item should not be found with ID 999");
    }

    @Test
    void testIncreaseItemQuantity(){
        sale.addItem(testItem, 1);
        sale.increaseItemQuantity("1", 2);

        SaleItem item = sale.findItemByID("1");
        assertEquals(3, item.getQuantity(), "Item quantity should be 3");

        Amount expectedTotal = new Amount(3 * 100 + (3 * 100 * 0.25));
        assertEquals(expectedTotal.getAmount(), sale.getSaleInfo().getRunningTotal().getAmount(), "Running total should be correct");
    }

    @Test
    void testApplySaleDiscount(){
        sale.addItem(testItem, 2);

        DiscountInfoDTO discountInfo = new DiscountInfoDTO(new Amount(20), 10, "Test Discount");

        sale.applySaleDiscount(discountInfo);

        // Expected: 250- 10% -20 = 250 - 25 -20 = 205
        Amount expectedAfterDiscount = new Amount(205);
        assertEquals(expectedAfterDiscount.getAmount(), sale.getTotalAfterDiscount().getAmount(), "Discount should be applied correctly");
    }

    @Test
    void testSetCustomerID(){
        sale.setCustomerID(12345);
        assertEquals(12345, sale.getSaleInfo().getCustomerID(), "Customer ID should be set correctly");
    }

    @Test
    void testGetSaleTime(){
        LocalDateTime before = LocalDateTime.now().minusSeconds(1);
        Sale newSale = new Sale(2);
        LocalDateTime after = LocalDateTime.now().plusSeconds(1);

        assertTrue(newSale.getSaleTime().isAfter(before) && newSale.getSaleTime().isBefore(after), "Sale time should be set correctly");
    }

    @Test
    void testGetSaleID(){
        assertEquals(1, sale.getSaleID(), "Sale ID should match the cunstructer argument");
    }
}