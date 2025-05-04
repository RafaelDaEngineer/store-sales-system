package se.kth.iv1350.storesalessystem.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import se.kth.iv1350.storesalessystem.integration.dto.DiscountInfoDTO;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.dto.SaleInfoDTO;

/**
 * Represents a sale in progress and its basic properties.
 */
public class Sale {
    private final LocalDateTime saleTime;
    private final int saleID;
    private final List<SaleItem> items;
    private int customerID;
    private Amount runningTotal;
    private Amount totalVAT;
    private final SaleDiscount saleDiscount;

    /**
     * Creates a new sale instance.
     *
     * @param saleID The ID of the sale.
     */
    public Sale(int saleID){
        this.saleID = saleID;
        this.saleTime = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.runningTotal = new Amount();
        this.totalVAT = new Amount();
        this.saleDiscount = new SaleDiscount();
    }

    /**
     * Adds an item to the current sale.
     *
     * @param item The item to add.
     * @param quantity The quantity of the item.
     */
    public void addItem(ItemDTO item, int quantity){
        SaleItem saleItem = new SaleItem(item, quantity);
        items.add(saleItem);
        updateRunningTotal();
    }

    /**
     * Updates the running total based on the current items in the sale.
     */
    public void updateRunningTotal(){
        Amount newTotal = new Amount();
        Amount newVAT = new Amount();
        Amount newRunningTotal = new Amount();

        for (SaleItem item : items) {
            newTotal = newTotal.plus(item.getTotalPrice());
            newVAT = newVAT.plus(item.getTotalVAT());
        }
        newRunningTotal = newRunningTotal.plus(newTotal).plus(newVAT);

        this.runningTotal = newRunningTotal;
        this.totalVAT = newVAT;
    }

    /**
     * Gets the customer ID of the sale.
     */
    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }

    public SaleInfoDTO getSaleInfo(){
        List<ItemDTO> itemDTOs = new ArrayList<>();
        for (SaleItem item : items){
            itemDTOs.add(item.getItemDTO());
        }
        return new SaleInfoDTO(saleID, runningTotal, itemDTOs, customerID, totalVAT);
    }

    /**
     * Gets the total amount for this sale after any discount.
     *
     * @return The total amount after discount.
     */
    public Amount getTotalAfterDiscount(){
        return saleDiscount.applyDiscountTo(runningTotal);
    }

    /**
     * Gets the total VAT amount for this sale.
     *
     * @return The total VAT amount.
     */
    public Amount getTotalVAT(){
        return new Amount(totalVAT.getAmount());
    }

    /**
     * Gets the local date and time when this sale was created.
     *
     * @return The date and time of the sale.
     */
    public LocalDateTime getSaleTime(){
        return saleTime;
    }

    /**
     * Gets an unmodifiable view of the items in the sale.
     *
     * @return The items in this sale.
     */
    public List<SaleItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Gets the ID of this sale.
     *
     * @return The ID of this sale.
     */
    public int getSaleID(){
        return saleID;
    }

    /**
     * Finds an item in the sale by its ID.
     * @param itemID The ID of the item to find.
     * @return The sale item if found, null otherwise.
     */
    public SaleItem findItemByID(String itemID) {
        for (SaleItem item : items) {
            if (item.getItemDTO().getItemID().equals(itemID)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Increases the quantity of an existing item.
     * @param itemID The ID of the item to increase.
     * @param additionalQuantity The additional quantity to add.
     */
    public void increaseItemQuantity(String itemID, int additionalQuantity) {
        SaleItem existingItem = findItemByID(itemID);
        if (existingItem != null) {
            // Since SaleItem is immutable, we need to create a new one
            // and replace the old one
            ItemDTO itemInfo = existingItem.getItemDTO();
            int newQuantity = existingItem.getQuantity() + additionalQuantity;

            // Remove old item
            items.remove(existingItem);

            // Add new item with updated quantity
            SaleItem updatedItem = new SaleItem(itemInfo, newQuantity);
            items.add(updatedItem);

            // Update running total
            updateRunningTotal();
        }
    }

    /**
     * Applies a discount to this sale.
     * @param discountInfo The discount information to apply.
     */
    public void applySaleDiscount(DiscountInfoDTO discountInfo) {
        saleDiscount.setDiscountInfo(discountInfo);
    }
}
