package se.kth.iv1350.storesalessystem.integration.dto;

import se.kth.iv1350.storesalessystem.model.Amount;

/**
 * Data Transfer Object (DTO) for representing an item in the system.
 * This class is immutable and encapsulates all necessary information about the item,
 * including its ID, name, description, tax rate, and price.
 * The purpose of this class is to securely transfer item information within the application.
 */
public class ItemDTO {
    private final String description;
    private final String name;
    private final double tax;
    private final Amount price;
    private final String itemID;

    /**
     * Creates a new instance of {@code ItemDTO} to represent an item in the sales system.
     *
     * @param itemID The unique identifier for the item. This is used to distinguish the item within the inventory system.
     * @param name The name of the item, describing what the item is.
     * @param description A brief description of the item, providing additional details about its characteristics.
     * @param tax The tax rate applicable to the item, represented as a decimal (e.g., 0.25 for 25% tax).
     * @param price The price of the item represented as an {@code Amount} object. A defensive copy of this object will be created.
     */
    public ItemDTO(String itemID, String name, String description, double tax, Amount price) {
        this.itemID = itemID;
        this.name = name;
        this.description = description;
        this.tax = tax;
        this.price = new Amount(price.getAmount()); // Defensive copy.
    }

    /**
     * Retrieves the unique identifier of the item.
     *
     * @return The item ID as a string.
     */
    public String getItemID(){
        return itemID;
    }

    /**
     * Retrieves the name of the item.
     *
     * @return The name of the item as a string.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the item's description.
     * @return The item's description.
     */
    public String getDescription(){
        return description;
    }

    /**
     * Get the item's tax rate.
     * @return The item's tax rate.
     */
    public double getTax(){
        return tax;
    }

    /**
     * Get the item's price.
     * @return A copy of the item's price to protect encapsulation.
     */
    public Amount getPrice(){
        return new Amount(price.getAmount());
    }
}
