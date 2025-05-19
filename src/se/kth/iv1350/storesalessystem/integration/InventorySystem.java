package se.kth.iv1350.storesalessystem.integration;

import java.util.HashMap;
import java.util.Map;

import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.IdentifierException;
import se.kth.iv1350.storesalessystem.model.dto.SaleInfoDTO;

/**
 * Represents the inventory system of a sales application. This class serves as a centralized
 * storage and management system for tracking items available for sale. It enables operations
 * such as retrieving item information, adding new items, and updating inventory based on
 * sales data.
 */
public class InventorySystem {
    private final Map<String, ItemDTO> inventory;
    private static final String DATABASE_FAILURE_TRIGGER_ID = "DB-ERROR-999";

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
     * Retrieves information about an item in the inventory system based on its unique identifier.
     * If the specified item identifier does not exist, or if a database error occurs, the method
     * will throw an appropriate exception.
     *
     * @param itemID The unique identifier of the item whose information is to be retrieved.
     *               This identifier is used to locate the corresponding item in the inventory.
     * @return An {@code ItemDTO} object containing the details of the requested item, such as
     * its ID, name, description, tax rate, and price.
     * @throws IdentifierException If no item with the specified identifier exists in the inventory.
     * @throws DatabaseException   If a database operation fails during the execution of this method.
     */
    public ItemDTO getItemInfo(String itemID) throws IdentifierException, DatabaseException {
        if (DATABASE_FAILURE_TRIGGER_ID.equals(itemID)) {
            throw new DatabaseException("getItemInfo");
        }

        ItemDTO item = inventory.get(itemID);
        if (item == null) {
            throw new IdentifierException(itemID);
        }
        return item;
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
