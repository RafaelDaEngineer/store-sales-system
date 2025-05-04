package se.kth.iv1350.storesalessystem.model;

import se.kth.iv1350.storesalessystem.integration.Printer;
import se.kth.iv1350.storesalessystem.model.dto.ReceiptDTO;

/**
 * The ReceiptPrinter class is responsible for creating a receipt for a completed sale
 * and sending it to a printer for output.
 * This class uses a Printer instance to handle the receipt printing
 * and a Receipt instance to generate structured receipt data from the sale and payment information.
 */
public class ReceiptPrinter {
    private final Printer printer;

    /**
     * Creates a new instance of ReceiptPrinter, which handles the printing of receipts
     * by using the provided Printer instance.
     *
     * @param printer The Printer instance responsible for outputting the receipt details.
     */
    public ReceiptPrinter(Printer printer){
        this.printer = printer;
    }

    /**
     * Prints a receipt for a completed sale and the corresponding payment.
     * The method creates a receipt object using the provided sale and payment information,
     * generates a ReceiptDTO from that object, and sends it to the printer for output.
     *
     * @param sale The sale containing details like items, total amount, and VAT information.
     * @param amountPaid The amount paid by the customer for the sale.
     */
    public void printReceipt(Sale sale, Amount amountPaid){
        Receipt receipt = new Receipt(sale, amountPaid);
        ReceiptDTO receiptData = receipt.createReceiptDTO();
        printer.printReceipt(receiptData);
    }
}
