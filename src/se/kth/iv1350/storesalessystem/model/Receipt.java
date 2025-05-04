package se.kth.iv1350.storesalessystem.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.dto.ReceiptDTO;
import se.kth.iv1350.storesalessystem.model.dto.ReceiptItemDTO;

/**
 * Represents a receipt for a completed sale.
 */
public class Receipt {
    private final Sale sale;
    private final Amount amountPaid;
    private final Amount change;

    /**
     * Creates a new receipt for the given sale.
     * @param sale The completed sale.
     * @param amountPaid The amount paid for the sale.
     */
    public Receipt(Sale sale, Amount amountPaid){
        this.sale = sale;
        this.amountPaid = new Amount(amountPaid.getAmount());
        this.change = calculateChange();
    }

    /**
     * Calculates the change to return to the customer.
     *
     * @return The change amount.
     */
    private Amount calculateChange() {
        Amount totalAfterDiscount = sale.getTotalAfterDiscount();
        return amountPaid.minus(totalAfterDiscount);
    }

    /**
     * Creates a DTO with all receipt information.
     *
     * @return The receipt DTO.
     */
    public ReceiptDTO createReceiptDTO(){
        String dateTime = formatDateTime(sale.getSaleTime());
        List<ReceiptItemDTO> receiptItems = createReceiptItems();

        return new ReceiptDTO(dateTime, receiptItems, sale.getTotalAfterDiscount(), sale.getTotalVAT(), amountPaid, change);
    }

    private String formatDateTime(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    private List<ReceiptItemDTO> createReceiptItems(){
        List<ReceiptItemDTO> result = new ArrayList<>();
        for (SaleItem saleItem : sale.getItems()){
            ItemDTO item = saleItem.getItemDTO();
            int quantity = saleItem.getQuantity();
            result.add(new ReceiptItemDTO(item, quantity));
        }
        return result;
    }

    /**
     * Gets the change amount for this receipt.
     *
     * @return The change amount.
     */
    public Amount getChange() {
        return new Amount(change.getAmount());
    }

    /**
     * Gets the amount paid for this receipt.
     *
     * @return The amount paid.
     */
    public Amount getAmountPaid() {
        return new Amount(amountPaid.getAmount());
    }
}
