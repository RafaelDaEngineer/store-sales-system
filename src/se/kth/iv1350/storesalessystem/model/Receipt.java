package se.kth.iv1350.storesalessystem.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.dto.ReceiptDTO;

/**
 * Represents a receipt for a completed sale.
 */
public class Receipt {
    private final Sale sale;
    private final Amount amountPaid;
    private final Amount change;

    /**
     * Creates a new receipt for the given sale..
     * @param sale The completed sale.
     * @param amountPaid The amount paid for the sale.
     * @param change The change due for the sale.
     */
    public Receipt(Sale sale, Amount amountPaid, Amount change){
        this.sale = sale;
        this.amountPaid = amountPaid;
        this.change = calculateChange(amountPaid);
    }

    /**
     * Calculates the change to return to the customer.
     *
     * @param amountPaid The amount paid by the customer.
     * @return The change amount.
     */
    private Amount calculateChange(Amount amountPaid){
        return amountPaid.minus(sale.getTotalAfterDiscount());
    }

    /**
     * Creates a DTO with all receipt information.
     *
     * @return The receipt DTO.
     */
    public ReceiptDTO createReceiptDTO(){
        String dateTime = formatDateTime(sale.getSaleTime());
        List<ItemDTO> itemDTOS = extractItemDTOs();

        return new ReceiptDTO(dateTime, itemDTOS, sale.getTotalAfterDiscount(), sale.getTotalVAT(), amountPaid, change);
    }

    private String formatDateTime(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    private List<ItemDTO> extractItemDTOs(){
        List<ItemDTO> result = new ArrayList<>();
        for (SaleItem item : sale.getItems()){
            result.add(item.getItemDTO());
        }
        return result;
    }
}
