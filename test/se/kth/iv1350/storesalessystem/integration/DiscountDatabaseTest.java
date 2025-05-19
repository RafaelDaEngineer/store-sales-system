package se.kth.iv1350.storesalessystem.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.storesalessystem.integration.dto.DiscountInfoDTO;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.Amount;
import se.kth.iv1350.storesalessystem.model.dto.SaleInfoDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiscountDatabaseTest {
    private DiscountDatabase discountDatabase;
    private SaleInfoDTO saleInfo;

    @BeforeEach
    void setUp() {
        discountDatabase = DiscountDatabase.getInstance();

        List<ItemDTO> items = new ArrayList<>();
        items.add(new ItemDTO("1", "Test Item", "Description", 0.25, new Amount(100)));
        saleInfo = new SaleInfoDTO(1, new Amount(100), items, 12345, new Amount(25));
    }

    @Test
    void testFindDiscount() {
        // Customer ID 12345 falls in the 10000-19999 range, which gets a percentage discount
        DiscountInfoDTO discountInfo = discountDatabase.findDiscount(12345, saleInfo);

        assertNotNull(discountInfo, "Discount info should not be null");
        assertTrue(discountInfo.isApplicable(), "Discount should be applicable for customer ID 12345");
        assertEquals(10, discountInfo.getDiscountPercentage(), "Should have 10% discount");
        assertEquals("Percentage Discount", discountInfo.getDiscountType(), "Should be percentage discount");
    }

}