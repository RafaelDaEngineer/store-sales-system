package se.kth.iv1350.storesalessystem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.dto.ReceiptDTO;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {
    private Receipt receipt;
    private Sale sale;
    private Amount amountPaid;

    @BeforeEach
    void setUp() {
        sale = new Sale(1);
        ItemDTO item = new ItemDTO("1", "Test Item", "Test Description", 0.25, new Amount(100));
        sale.addItem(item, 2);

        amountPaid = new Amount(300);
        receipt = new Receipt(sale, amountPaid);
    }

    @Test
    void testCalculateChange(){
        Amount expectedChange = new Amount(50); // 300 - (2 * 100 + 25% VAT) = 300 -250 = 50:
        assertEquals(expectedChange.getAmount(), receipt.getChange().getAmount(), "Change should be correct");
    }

    @Test
    void testCreateReceiptDTO(){
        ReceiptDTO receiptDTO = receipt.createReceiptDTO();

        assertNotNull(receiptDTO, "ReceiptDTO should not be null");
        assertEquals(1, receiptDTO.getItems().size(), "Receipt should have one item");
        assertEquals(250.0, receiptDTO.getTotalAmount().getAmount(), "Total amount should be match");
        assertEquals(50.0, receiptDTO.getChange().getAmount(), "Change should be match");
        assertEquals(50.0, receiptDTO.getTotalVAT().getAmount(), "Total VAT should be match");
        assertEquals(300.0, receiptDTO.getTotalPaid().getAmount(), "Total paid should be match");
    }

    @Test
    void testGetAmountPaid(){
        assertEquals(amountPaid.getAmount(), receipt.getAmountPaid().getAmount(), "Amount paid should match constructor argument");
    }

    @Test
    void testDefensiveCopying(){
        Amount change = receipt.getChange();
        change = change.plus(new Amount(100));

        Amount originalChange = receipt.getChange();
        assertNotEquals(change.getAmount(), originalChange.getAmount(), "Modifying the returned change should not affect the original change" );
    }
}