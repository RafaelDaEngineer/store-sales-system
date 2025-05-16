package se.kth.iv1350.storesalessystem.model.dto;

import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.Amount;

/**
 * A Data Transfer Object (DTO) representing an individual item included in a receipt.
 * This class encapsulates the details of a purchased item, including the item itself
 * and the quantity purchased. It provides methods to access the item details, quantity,
 * and the total price of the item based on the quantity.
 * The primary usage of this class is to transfer information regarding a specific purchase
 * within the store sales system.
 */
public record ReceiptItemDTO(ItemDTO item, int quantity) {
    /**
     * Instantiates a new {@code ReceiptItemDTO}, representing an item included in a receipt.
     *
     * @param item     The {@code ItemDTO} representing the details of the item being purchased,
     *                 such as its name, description, price, and tax rate.
     * @param quantity The quantity of the item purchased in the sale.
     */
    public ReceiptItemDTO {
    }

    /**
     * Retrieves the item details encapsulated in a {@code ItemDTO} object.
     *
     * @return The {@code ItemDTO} representing the details of the item associated with this receipt item.
     */
    @Override
    public ItemDTO item() {
        return item;
    }

    /**
     * Retrieves the quantity of the item purchased.
     *
     * @return The quantity of the item associated with this receipt item.
     */
    @Override
    public int quantity() {
        return quantity;
    }

    /**
     * Calculates the total price of the item based on its price and the quantity purchased.
     *
     * @return The total price as an {@code Amount} object, which represents the product of the item's price and the quantity purchased.
     */
    public Amount getTotalPrice() {
        return item.price().multiply(quantity);
    }
}
