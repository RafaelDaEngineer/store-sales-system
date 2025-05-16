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
public record ReceiptDTO(String dateTime, List<ReceiptItemDTO> items, Amount totalAmount, Amount totalVAT,
                         Amount totalPaid, Amount change) {
    /**
     * Creates an instance of {@code ReceiptDTO}, representing the data of a completed sale.
     *
     * @param dateTime    The date and time when the sale was completed.
     * @param items       The list of items purchased during the sale.
     * @param totalAmount The total cost of the items in the sale.
     * @param totalVAT    The total value-added tax for the sale.
     * @param totalPaid   The total amount paid by the customer.
     * @param change      The change to be given back to the customer.
     */
    public ReceiptDTO(String dateTime, List<ReceiptItemDTO> items, Amount totalAmount, Amount totalVAT, Amount totalPaid, Amount change) {
        this.dateTime = dateTime;
        this.items = new ArrayList<>(items);
        this.totalAmount = new Amount(totalAmount.getAmount());
        this.totalVAT = new Amount(totalVAT.getAmount());
        this.totalPaid = new Amount(totalPaid.getAmount());
        this.change = new Amount(change.getAmount());
    }

    /**
     * Get the date and time of the sale.
     *
     * @return The date and time of the sale.
     */
    @Override
    public String dateTime() {
        return dateTime;
    }

    /**
     * Get the items purchased in the sale.
     *
     * @return An unmodifiable view of the items purchased in the sale.
     */
    @Override
    public List<ReceiptItemDTO> items() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Get the total amount for the sale.
     *
     * @return A copy of the total amount.
     */
    @Override
    public Amount totalAmount() {
        return new Amount(totalAmount.getAmount());
    }

    /**
     * Get the total VAT for the sale.
     *
     * @return A copy of the total VAT.
     */
    @Override
    public Amount totalVAT() {
        return new Amount(totalVAT.getAmount());
    }

    /**
     * Get the total amount paid for the sale.
     *
     * @return A copy of the total amount paid.
     */
    @Override
    public Amount totalPaid() {
        return new Amount(totalPaid.getAmount());
    }

    /**
     * Get the change amount for the sale.
     *
     * @return A copy of the change amount.
     */
    @Override
    public Amount change() {
        return new Amount(change.getAmount());
    }
}
