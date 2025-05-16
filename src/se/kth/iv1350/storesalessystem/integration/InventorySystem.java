package se.kth.iv1350.storesalessystem.integration;

import java.util.HashMap;
import java.util.Map;

import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.dto.SaleInfoDTO;

/**
 * Represents the inventory system of a sales application. This class serves as a centralized
 * storage and management system for tracking items available for sale. It enables operations
 * such as retrieving item information, adding new items, and updating inventory based on
 * sales data.
 */
public class InventorySystem {
    private final Map<String, ItemDTO> inventory;

    /**
     * Creates a new instance of the InventorySystem class.
     * This constructor initializes the inventory as an empty collection,
     * establishing a system to store and manage information about items
     * available for sale in the sales application.
     */
    public InventorySystem() {
        this.inventory = new HashMap<>();
    }

    /**
     * Retrieves detailed information about a specific item from the inventory
     * based on its unique identifier.
     *
     * @param itemID The unique identifier of the item to retrieve information for.
     *               If the item does not exist in the inventory, {@code null} is returned.
     * @return An {@code ItemDTO} containing information about the requested item,
     * or {@code null} if no item is found for the given ID.
     */
    public ItemDTO getItemInfo(String itemID) {
        return inventory.get(itemID);
    }

    /**
     * Adds a new item to the inventory. The item is stored in the inventory using its
     * unique identifier as the key.
     *
     * @param item The {@code ItemDTO} representing the item to be added to the inventory.
     *             This object contains details about the item, such as its identifier,
     *             name, description, price, and tax rate.
     */
    public void addItem(ItemDTO item) {
        inventory.put(item.itemID(), item);
    }

    /**
     * Updates the inventory based on the provided sale information. This method adjusts
     * the inventory quantities to reflect the items sold in the specified sale.
     *
     * @param saleInfoDTO The data transfer object containing information about the completed sale.
     *                    This includes details such as the sold items, total amount, and VAT.
     */
    public void updateInventory(SaleInfoDTO saleInfoDTO) {
    }
}
