package se.kth.iv1350.storesalessystem.integration;

import java.util.List;
import se.kth.iv1350.storesalessystem.model.dto.ReceiptItemDTO;
import se.kth.iv1350.storesalessystem.model.dto.ReceiptDTO;


public class Printer {
    public Printer(){

    }

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

    private String formatAmount(double amount){
        return String.format("%.2f", amount).replace(".", ":");
    }

    private String formatDateTime(String dateTime){
        return dateTime.substring(0,10) + " " + dateTime.substring(11,16);
    }
}
