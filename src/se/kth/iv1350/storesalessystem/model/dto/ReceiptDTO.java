package se.kth.iv1350.storesalessystem.model.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import se.kth.iv1350.storesalessystem.model.Amount;


/**
 * A Data Transfer Object (DTO) representing a receipt in the store sales system.
 * This class contains all necessary details about a completed sale,
 * including the purchased items, financial information, and transaction metadata.
 */
public class ReceiptDTO {
    private final  String dateTime;
    private final List<ReceiptItemDTO> items;
    private final Amount totalAmount;
    private final Amount totalVAT;
    private final Amount totalPaid;
    private final Amount change;

    /**
     * Creates an instance of {@code ReceiptDTO}, representing the data of a completed sale.
     *
     * @param dateTime The date and time when the sale was completed.
     * @param items The list of items purchased during the sale.
     * @param totalAmount The total cost of the items in the sale.
     * @param totalVAT The total value-added tax for the sale.
     * @param totalPaid The total amount paid by the customer.
     * @param change The change to be given back to the customer.
     */
    public ReceiptDTO(String dateTime, List<ReceiptItemDTO> items, Amount totalAmount, Amount totalVAT, Amount totalPaid, Amount change){
        this.dateTime = dateTime;
        this.items = new ArrayList<>(items);
        this.totalAmount = new Amount(totalAmount.getAmount());
        this.totalVAT = new Amount(totalVAT.getAmount());
        this.totalPaid = new Amount(totalPaid.getAmount());
        this.change = new Amount(change.getAmount());
    }

    /**
     * Get the date and time of the sale.
     * @return The date and time of the sale.
     */
    public String getDateTime(){
        return dateTime;
    }

    /**
    * Get the items purchased in the sale.
    * @return An unmodifiable view of the items purchased in the sale.
     */
    public List<ReceiptItemDTO> getItems(){
        return Collections.unmodifiableList(items);
    }

    /**
     * Get the total amount for the sale.
     * @return A copy of the total amount.
     */
    public Amount getTotalAmount(){
        return new Amount(totalAmount.getAmount());
    }

    /**
     * Get the total VAT for the sale.
     * @return A copy of the total VAT.
     */
    public Amount getTotalVAT(){
        return new Amount(totalVAT.getAmount());
    }

    /**
     * Get the total amount paid for the sale.
     * @return A copy of the total amount paid.
     */
    public Amount getTotalPaid(){
        return new Amount(totalPaid.getAmount());
    }

    /**
     * Get the change amount for the sale.
     * @return A copy of the change amount.
     */
    public Amount getChange(){
        return new Amount(change.getAmount());
    }
}
