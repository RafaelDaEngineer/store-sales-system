package se.kth.iv1350.storesalessystem.model.dto;

import java.util.Collection;

import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.Amount;

/**
 * Data Transfer Object (DTO) containing summary details about an ongoing sale.
 * This class is immutable and provides methods to retrieve information about the sale
 * such as the running total, customer ID, and total VAT. The purpose of this class
 * is to facilitate secure transfer of sale-related data within the system.
 */
public record SaleInfoDTO(int saleID, Amount runningTotal, Collection<ItemDTO> items, int customerID, Amount totalVAT) {

    /**
     * Creates an instance of {@code SaleInfoDTO}, representing summarized data about an ongoing sale.
     *
     * @param saleID       The unique identifier of the sale.
     * @param runningTotal The running total amount of the sale.
     * @param items        A collection of items included in the ongoing sale.
     * @param customerID   The ID of the customer involved in the sale.
     * @param totalVAT     The total value-added tax amount for the sale.
     */
    public SaleInfoDTO(int saleID, Amount runningTotal, Collection<ItemDTO> items, int customerID, Amount totalVAT) {
        this.saleID = saleID;
        this.runningTotal = new Amount(runningTotal.getAmount());
        this.items = items;
        this.customerID = customerID;
        this.totalVAT = new Amount(totalVAT.getAmount());
    }

    /**
     * Get the running total of the sale.
     *
     * @return A copy of the running total amount.
     */
    public Amount getRunningTotal() {
        return new Amount(runningTotal.getAmount());
    }

    /**
     * Get the ID of the customer making the purchase.
     *
     * @return The ID of the customer.
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Get the total VAT amount.
     *
     * @return A copy of the total VAT amount.
     */
    public Amount getTotalVAT() {
        return new Amount(totalVAT.getAmount());
    }
}
