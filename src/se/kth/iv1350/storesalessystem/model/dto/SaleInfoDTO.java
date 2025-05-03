package se.kth.iv1350.storesalessystem.model.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Collection;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.Amount;

/**
 * Data Transfer Object containing information about a sale.
 */
public class SaleInfoDTO {
    private final Amount runningTotal;
    private final int saleID;
    private final List<ItemDTO> items;
    private final int customerID;
    private final Amount totalVAT;

    /**
     * Creates a new instance with the specified sale information.
     *
     * @param runningTotal The running total of the sale.
     * @param saleID The ID of the sale.
     * @param items The items in the sale.
     * @param customerID The ID of the customer making the purchase.
     * @param totalVAT The total VAT the sale.
     */
    public SaleInfoDTO(int saleID, Amount runningTotal, Collection<ItemDTO> items, int customerID, Amount totalVAT){
        this.runningTotal = new Amount(runningTotal.getAmount());
        this.saleID = saleID;
        this.items = new ArrayList<>(items); // Create defensive copy.
        this.customerID = customerID;
        this.totalVAT = new Amount(totalVAT.getAmount());
    }

    /**
     * Get the sale ID.
     * @return The sale ID.
     */
    public int getSaleID(){
        return saleID;
    }

    /**
     * Get the running total of the sale.
     * @return A copy of the running total amount.
     */
    public Amount getRunningTotal(){
        return new Amount(runningTotal.getAmount());
    }

    /**
     * Get the items in the sale.
     * @return An unmodifiable view of the items in the sale.
     */
    public List<ItemDTO> getItems(){
        return Collections.unmodifiableList(items);
    }

    /**
     * Get the ID of the customer making the purchase.
     * @return The ID of the customer.
     */
    public int getCustomerID(){
        return customerID;
    }

    /**
     * Get the total VAT amount.
     * @return A copy of the total VAT amount.
     */
    public Amount getTotalVAT(){
        return new Amount(totalVAT.getAmount());
    }
}
