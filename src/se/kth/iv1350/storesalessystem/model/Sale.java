package se.kth.iv1350.storesalessystem.model;

import java.time.LocalDateTime;
import java.util.*;

import se.kth.iv1350.storesalessystem.integration.dto.DiscountInfoDTO;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.dto.SaleInfoDTO;

/**
 * Represents a sale transaction. A sale consists of multiple items,
 * may have associated customer information, keeps track of the
 * running total and applicable VAT, and can apply discounts.
 */
public class Sale {
    private final LocalDateTime saleTime;
    private final int saleID;
    private final Map<String, SaleItem> items;
    private int customerID;
    private Amount runningTotal;
    private Amount totalVAT;
    private final SaleDiscount saleDiscount;

    /**
     * Creates a new instance of the Sale class with the specified sale ID.
     * Initializes the sale's creation time, item list, running total, VAT, and sale discount.
     *
     * @param saleID The unique identifier for this sale.
     */
    public Sale(int saleID) {
        this.saleID = saleID;
        this.saleTime = LocalDateTime.now();
        this.items = new LinkedHashMap<>();
        this.runningTotal = new Amount();
        this.totalVAT = new Amount();
        this.saleDiscount = new SaleDiscount();
    }

    /**
     * Adds an item to the current sale along with its specified quantity.
     * Updates the running total of the sale based on the added item.
     *
     * @param item     The item to be added to the sale, represented as an ItemDTO.
     * @param quantity The quantity of the item to be added.
     */
    public void addItem(ItemDTO item, int quantity) {
        SaleItem saleItem = new SaleItem(item, quantity);
        items.put(item.itemID(), saleItem);
        updateRunningTotal();
    }

    /**
     * Updates the running total and total VAT for the current sale.
     * This method calculates the total price and VAT for all items in the sale.
     * It then updates the running total and total VAT fields with the computed values.
     * The running total is calculated as the sum of:
     * - The total price of all items.
     * - The total VAT of all items.
     */
    public void updateRunningTotal() {
        Amount newTotal = new Amount();
        Amount newVAT = new Amount();
        Amount newRunningTotal = new Amount();

        for (SaleItem item : items.values()) {
            newTotal = newTotal.plus(item.getTotalPrice());
            newVAT = newVAT.plus(item.getTotalVAT());
        }
        newRunningTotal = newRunningTotal.plus(newTotal).plus(newVAT);

        this.runningTotal = newRunningTotal;
        this.totalVAT = newVAT;
    }

    /**
     * Sets the customer ID associated with the current sale.
     *
     * @param customerID The unique identifier for the customer participating in this sale.
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Retrieves detailed information about the current sale, including sale ID, running total,
     * items in the sale, customer ID, and total VAT.
     *
     * @return A {@code SaleInfoDTO} object containing summarized details of the current sale.
     */
    public SaleInfoDTO getSaleInfo() {
        List<ItemDTO> itemDTOs = new ArrayList<>();
        for (SaleItem item : items.values()) {
            itemDTOs.add(item.getItemDTO());
        }
        return new SaleInfoDTO(saleID, runningTotal, itemDTOs, customerID, totalVAT);
    }

    /**
     * Retrieves the total amount after applying the discount to the running total of the sale.
     * The discount is calculated based on the current sale's discount information.
     *
     * @return The total monetary amount after the discount, represented as an {@code Amount} object.
     */
    public Amount getTotalAfterDiscount() {
        return saleDiscount.applyDiscountTo(runningTotal);
    }

    /**
     * Retrieves the total VAT (Value Added Tax) for the current sale.
     * This amount represents the total tax collected based on the sale's items
     * and their respective tax rates.
     *
     * @return The total VAT as an {@code Amount} object.
     */
    public Amount getTotalVAT() {
        return new Amount(totalVAT.getAmount());
    }

    /**
     * Retrieves the time when the sale was created.
     *
     * @return The creation timestamp of the sale as a {@code LocalDateTime} object.
     */
    public LocalDateTime getSaleTime() {
        return saleTime;
    }

    /**
     * Retrieves the list of all items currently included in the sale.
     * The returned list is unmodifiable to ensure the integrity of the sale's item list.
     *
     * @return An unmodifiable list of {@code SaleItem} objects representing the items in the sale.
     */
    public List<SaleItem> getItems() {
        return new ArrayList<>(items.values());
    }

    /**
     * Retrieves the unique identifier for the current sale.
     *
     * @return The sale ID as an integer value.
     */
    public int getSaleID() {
        return saleID;
    }

    /**
     * Searches for a {@code SaleItem} in the current sale by its unique identifier.
     * If the item with the specified ID exists, it will be returned; otherwise, {@code null} is returned.
     *
     * @param itemID The unique identifier of the item to search for.
     * @return The {@code SaleItem} matching the given ID, or {@code null} if no matching item is found.
     */
    public SaleItem findItemByID(String itemID) throws IdentifierException {
        SaleItem item = items.get(itemID);
        if (item == null) {
            throw new IdentifierException(itemID);
        }
        return item;
    }

    /**
     * Increases the quantity of a specific item in the current sale.
     * If the item exists, its quantity is updated by the specified amount,
     * and the running total is recalculated.
     *
     * @param itemID             The unique identifier of the item whose quantity should be increased.
     * @param additionalQuantity The amount by which the item's quantity should be increased.
     */
    public void increaseItemQuantity(String itemID, int additionalQuantity) {
        SaleItem existingItem = items.get(itemID);
        if (existingItem != null) {
            ItemDTO itemInfo = existingItem.getItemDTO();
            int newQuantity = existingItem.getQuantity() + additionalQuantity;

            SaleItem updatedItem = new SaleItem(itemInfo, newQuantity);
            items.put(itemID, updatedItem);

            updateRunningTotal();
        }
    }

    /**
     * Applies a sale discount to the current sale by setting the provided discount information.
     *
     * @param discountInfo The information about the discount to be applied to the sale,
     *                     represented as a {@code DiscountInfoDTO}.
     */
    public void applySaleDiscount(DiscountInfoDTO discountInfo) {
        saleDiscount.setDiscountInfo(discountInfo);
    }
}
