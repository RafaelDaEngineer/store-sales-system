package se.kth.iv1350.storesalessystem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

class SaleItemTest {
    private SaleItem saleItem;
    private ItemDTO itemDTO;

    @BeforeEach
    void setUp() {
        itemDTO = new ItemDTO("1", "Test Item", "Test Description", 0.25, new Amount(100));
        saleItem = new SaleItem(itemDTO, 3);
    }

    @Test
    void testGetTotalPrice() {
        Amount expectedPrice = new Amount(300);
        assertEquals(expectedPrice.getAmount(), saleItem.getTotalPrice().getAmount(), "Total price should be items * quantity");
    }

    @Test
    void testGetTotalVAT() {
        Amount expectedVAT = new Amount(75);
        assertEquals(expectedVAT.getAmount(), saleItem.getTotalVAT().getAmount(), "Total VAT should be items * quantity * VAT");
    }

    @Test
    void testGetItemDTO() {
        ItemDTO retrievedDTO = saleItem.getItemDTO();
        assertEquals(itemDTO.itemID(), retrievedDTO.itemID(), "ItemDTO should be the same as the one passed to the constructor");
        assertEquals(itemDTO.name(), retrievedDTO.name(), "ItemDTO should be the same as the one passed to the constructor");
    }

    @Test
    void testGetQuantity() {
        assertEquals(3, saleItem.getQuantity(), "Quantity should be the same as the one passed to the constructor");
    }
}