package se.kth.iv1350.storesalessystem.model;

import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;

/**
 * Represents an item in a sale, including the item details and its quantity.
 * This class is immutable and provides methods to calculate the total price and VAT for the item.
 */
public class SaleItem {
    private final ItemDTO item;
    private final int quantity;

    /**
     * Creates a new instance of {@code SaleItem}, representing an item in a sale.
     *
     * @param item The {@code ItemDTO} representing the details of the item being sold.
     * @param quantity The quantity of the item in this sale.
     */
    public SaleItem(ItemDTO item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Calculates the total price of the sale item by multiplying the item's price by its quantity.
     *
     * @return The total price as an {@code Amount} object.
     */
    public Amount getTotalPrice(){
        return item.getPrice().multiply(quantity);
    }

    /**
     * Calculates the total VAT (Value Added Tax) for the sale item.
     * The VAT is determined by multiplying the item's price by its tax rate
     * and then by the quantity of the item in the sale.
     *
     * @return The total VAT as an {@code Amount} object.
     */
    public Amount getTotalVAT(){
        double vatAmount = item.getPrice().getAmount() * item.getTax() * quantity;
        return new Amount(vatAmount);
    }

    /**
     * Retrieves the {@code ItemDTO} object representing the item associated with this sale item.
     *
     * @return The {@code ItemDTO} containing the details of the item.
     */
    public ItemDTO getItemDTO(){
        return item;
    }

    /**
     * Retrieves the quantity of the item in the sale.
     *
     * @return The quantity of the item as an integer.
     */
    public int getQuantity(){
        return quantity;
    }
}
