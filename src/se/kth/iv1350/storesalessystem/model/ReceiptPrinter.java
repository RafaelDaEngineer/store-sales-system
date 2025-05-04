package se.kth.iv1350.storesalessystem.model;

import se.kth.iv1350.storesalessystem.integration.Printer;
import se.kth.iv1350.storesalessystem.model.dto.ReceiptDTO;

public class ReceiptPrinter {
    private Printer printer;

    public ReceiptPrinter(Printer printer){
        this.printer = printer;
    }

    public void printReceipt(Sale sale, Amount amountPaid){
        Receipt receipt = new Receipt(sale, amountPaid);
        ReceiptDTO receiptData = receipt.createReceiptDTO();
        printer.printReceipt(receiptData);
    }
}
