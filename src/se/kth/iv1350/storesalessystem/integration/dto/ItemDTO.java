package se.kth.iv1350.storesalessystem.integration.dto;

import se.kth.iv1350.storesalessystem.model.Amount;

/**
 * Data Transfer Object containing information about an item in the sales system.
 */

public class ItemDTO {
    private final String description;
    private final String name;
    private final double tax;
    private final Amount price;
    private final String itemID;

    /**
    * Creates a new instance representing an item.
    *
    * @param itemID The ID of the item.
    * @param description The description of the item.
    * @param tax The tax rate of the item.
    * @param price The price of the item.
    */
    public ItemDTO(String itemID, String name, String description, double tax, Amount price) {
        this.itemID = itemID;
        this.name = name;
        this.description = description;
        this.tax = tax;
        this.price = new Amount(price.getAmount()); // Defensive copy.
    }

    /**
     * Get the item's ID.
     * @return The item's ID.
     */
    public String getItemID(){
        return itemID;
    }

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
     * @return A copy of the item's price.
     */
    public Amount getPrice(){
        return new Amount(price.getAmount()); //Return a copy to protect encapsulation.
    }
}
