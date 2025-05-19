package se.kth.iv1350.storesalessystem.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.dto.ReceiptDTO;
import se.kth.iv1350.storesalessystem.model.dto.ReceiptItemDTO;

/**
 * Represents a receipt for a completed sale, including details of the sale,
 * the amount paid by the customer, and the change to be returned.
 */
public class Receipt {
    private final Sale sale;
    private final Amount amountPaid;
    private final Amount change;

    /**
     * Creates a new {@code Receipt} instance representing the details of a completed sale.
     *
     * @param sale       The {@code Sale} object associated with this receipt, containing the details of the completed transaction.
     * @param amountPaid The {@code Amount} object representing the total payment made by the customer.
     */
    public Receipt(Sale sale, Amount amountPaid) {
        this.sale = sale;
        this.amountPaid = new Amount(amountPaid.getAmount());
        this.change = calculateChange();
    }

    /**
     * Calculates the change to be returned to the customer based on the total amount
     * after discounts and the amount paid.
     *
     * @return The change amount as an {@code Amount} object.
     */
    private Amount calculateChange() {
        Amount totalAfterDiscount = sale.getTotalAfterDiscount();
        return amountPaid.minus(totalAfterDiscount);
    }

    /**
     * Creates a {@code ReceiptDTO} object that contains the details of the completed sale,
     * including the date and time of the sale, the purchased items, the total cost after discount,
     * the total VAT, the total payment made, and the change to be returned.
     *
     * @return A {@code ReceiptDTO} object that holds the summary of the completed sale.
     */
    public ReceiptDTO createReceiptDTO() {
        String dateTime = formatDateTime(sale.getSaleTime());
        List<ReceiptItemDTO> receiptItems = createReceiptItems();
        String discountDescription = sale.getDiscountDescription();

        return new ReceiptDTO(dateTime, receiptItems, sale.getTotalAfterDiscount(), sale.getTotalVAT(), amountPaid, change, discountDescription);
    }

    /**
     * Formats the given {@code LocalDateTime} into a string representation using the
     * pattern "yyyy-MM-dd HH:mm:ss".
     *
     * @param dateTime The {@code LocalDateTime} object to be formatted.
     * @return A formatted date and time string.
     */
    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    /**
     * Creates a list of receipt items, each represented by a {@code ReceiptItemDTO},
     * based on the items included in the associated {@code Sale}.
     * For every item in the sale, its corresponding {@code ItemDTO} and quantity
     * are used to instantiate a {@code ReceiptItemDTO}, which is then added to the list.
     *
     * @return A list of {@code ReceiptItemDTO} objects representing the receipt items
     * included in the sale.
     */
    private List<ReceiptItemDTO> createReceiptItems() {
        List<ReceiptItemDTO> result = new ArrayList<>();
        for (SaleItem saleItem : sale.getItems()) {
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
