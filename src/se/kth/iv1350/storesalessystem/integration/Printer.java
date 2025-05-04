package se.kth.iv1350.storesalessystem.integration;

import java.util.List;
import se.kth.iv1350.storesalessystem.model.dto.ReceiptItemDTO;
import se.kth.iv1350.storesalessystem.model.dto.ReceiptDTO;

/**
 * The Printer class is responsible for generating and printing receipts.
 */
public class Printer {
    public Printer(){

    }

    /**
     * Prints the receipt details for a sale, including the items purchased,
     * total amount, VAT, payment, and change.
     *
     * @param receiptData A {@link ReceiptDTO} object containing the sale information,
     * including the date and time, items purchased, total amount, VAT, total paid, and change.
     */
    public void printReceipt(ReceiptDTO receiptData){
        System.out.println("------------------------ Begin receipt ------------------------");
        System.out.println("Time of Sale: " + formatDateTime(receiptData.getDateTime()) + "\n");

        List<ReceiptItemDTO> items = receiptData.getItems();
        for (ReceiptItemDTO item : items) {
            System.out.printf("%s\t\t %d x %.2f\t\t %s SEK%n",
                            item.getItem().getName(),
                            item.getQuantity(),
                            item.getItem().getPrice().getAmount(),
                            formatAmount(item.getTotalPrice().getAmount()));
        }
        System.out.println();
        System.out.println("Total: " + formatAmount(receiptData.getTotalAmount().getAmount()) + " SEK");
        System.out.println("VAT: " + formatAmount(receiptData.getTotalVAT().getAmount()) + "\n");
        System.out.println("Cash: " + formatAmount(receiptData.getTotalPaid().getAmount()) + " SEK");
        System.out.println("Change: " + formatAmount(receiptData.getChange().getAmount()) + " SEK");
        System.out.println("------------------------ End receipt --------------------------");
    }

    /**
     * Formats a given monetary amount into a string representation with two decimal places,
     * replacing the decimal point with a colon.
     *
     * @param amount The monetary amount to be formatted.
     * @return A string representing the formatted amount, with two decimal places and a colon
     *         instead of a decimal point.
     */
    private String formatAmount(double amount){
        return String.format("%.2f", amount).replace(".", ":");
    }

    /**
     * Formats a given date and time string into a specific format.
     * The output format is "YYYY-MM-DD HH:MM", where the date and time components
     * are extracted from the input string.
     *
     * @param dateTime The original date and time string, expected in the format "YYYY-MM-DDTHH:MM:SS".
     * @return A formatted string representing the date and time in the format "YYYY-MM-DD HH:MM".
     */
    private String formatDateTime(String dateTime){
        return dateTime.substring(0,10) + " " + dateTime.substring(11,16);
    }
}
