package se.kth.iv1350.storesalessystem.model;

import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;

/**
 * Represents an item in a sale with its quantity.
 */
public class SaleItem {
    private final ItemDTO item;
    private final int quantity;

    /**
     * Creates a new sale item.
     *
     * @param item The item in the sale.
     * @param quantity The quantity of the item in the sale.
     */
    public SaleItem(ItemDTO item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Gets the total price for this item (price * quantity).
     * @return The total price for this item.
     */
    public Amount getTotalPrice(){
        return item.getPrice().multiply(quantity);
    }

    public Amount getTotalVAT(){
        double vatAmount = item.getPrice().getAmount() * item.getTax() * quantity;
        return new Amount(vatAmount);
    }

    /**
     * Gets the item DTO or this sale item.
     *
     * @return The item DTO.
     */
    public ItemDTO getItemDTO(){
        return item;
    }

    /**
     * Gets the quantity of this item in the sale.
     *
     * @return The quantity of this item in the sale.
     */
    public int getQuantity(){
        return quantity;
    }
}
