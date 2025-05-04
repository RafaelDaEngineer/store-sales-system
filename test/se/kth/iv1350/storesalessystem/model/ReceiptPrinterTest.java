package se.kth.iv1350.storesalessystem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.storesalessystem.integration.Printer;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptPrinterTest {
    private ReceiptPrinter receiptPrinter;
    private Sale sale;

    @BeforeEach
    void setUp() {
        Printer printer = new Printer();
        receiptPrinter = new ReceiptPrinter(printer);
        sale = new Sale(1);
        ItemDTO item = new ItemDTO("1", "Test Item", "Test Description", 0.25, new Amount(100));
        sale.addItem(item, 2);
    }

    @Test
    void testPrintReceipt(){
        // Since the actual implementation in Printer mostly involves System.out,
        // we can only test that the method doesn't throw exceptions
        Amount amountPaid = new Amount(300);
        assertDoesNotThrow(() -> receiptPrinter.printReceipt(sale, amountPaid),
                "Printing receipt should not throw exceptions");
    }
}