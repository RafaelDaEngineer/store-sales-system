package se.kth.iv1350.storesalessystem.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    private SaleDiscount saleDiscount;

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

        for (SaleItem item : items) {
            newTotal = newTotal.plus(item.getTotalPrice());
            newVAT = newVAT.plus(item.getTotalVAT());
        }

        this.runningTotal = newTotal;
        this.totalVAT = newVAT;
    }

    /**
     * Gets the customer ID of the sale.
     * @return customerID The customer ID.
     */
    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }

    /**
     * Gets information about the current sale for discount calculation.
     * @return Sale information formatted for discount requests.
     */
    public SaleInfoDTO getSaleInfoForDiscount(){
        return new SaleInfoDTO(
                saleID,
                runningTotal,
                extractItemDTOs(),
                customerID,
                totalVAT
        );
    }

    /**
     * Gets information about the current sale for accounting purposes.
     *
     * @return Sale information formatted for accounting.
     */
    public SaleInfoDTO getSaleInfoForAccounting() {
        return new SaleInfoDTO(
                saleID,
                getTotalAfterDiscount(),
                extractItemDTOs(),
                customerID,
                totalVAT
        );
    }

    /**
     * Gets information about the current sale for inventory purposes.
     *
     * @return Sale information formatted for inventory.
     */
    public SaleInfoDTO getSaleInfoForInventory() {
        return new SaleInfoDTO(
                saleID,
                runningTotal,
                extractItemDTOs(),
                customerID,
                totalVAT
        );
    }


    public List<ItemDTO> extractItemDTOs(){
        List<ItemDTO> itemDTOs = new ArrayList<>();
        for (SaleItem item : items){
            itemDTOs.add(item.getItemDTO());
        }
        return itemDTOs;
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
     * Gets the customer ID for this sale.
     *
     * @return The customer ID.
     */
    public int getCustomerID(){
        return customerID;
    }
}
