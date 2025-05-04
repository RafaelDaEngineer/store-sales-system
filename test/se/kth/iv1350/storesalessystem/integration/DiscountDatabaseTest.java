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
    void setUp(){
        discountDatabase = new DiscountDatabase();

        List<ItemDTO> items = new ArrayList<>();
        items.add(new ItemDTO("1", "Test Item", "Description", 0.25, new Amount(100)));
        saleInfo = new SaleInfoDTO(1, new Amount(100), items, 12345, new Amount(25));
    }

    @Test
    void testFindDiscount(){
        // Since the implementation is just a placeholder, we can only verify it doesn't throw
        // exceptions and returns a non-null object
        DiscountInfoDTO discountInfo = discountDatabase.findDiscount(12345, saleInfo);

        assertNotNull(discountInfo, "Discount info should not be null");
        // Default placeholder implementation returns empty discount
        assertFalse(discountInfo.isApplicable(), "Placeholder discount should not be applicable");
    }

}