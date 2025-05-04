package se.kth.iv1350.storesalessystem.model.dto;

import java.util.Collection;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.Amount;

/**
 * Data Transfer Object containing information about a sale.
 */
public class SaleInfoDTO {
    private final Amount runningTotal;
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
        this.customerID = customerID;
        this.totalVAT = new Amount(totalVAT.getAmount());
    }

    /**
     * Get the running total of the sale.
     * @return A copy of the running total amount.
     */
    public Amount getRunningTotal(){
        return new Amount(runningTotal.getAmount());
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
