package se.kth.iv1350.storesalessystem.integration.dto;

import se.kth.iv1350.storesalessystem.model.Amount;

/**
 * Data Transfer Object containing information about a discount.
 * This class is immutable and preserves encapsulation through defensive copying.
 */
public class DiscountInfoDTO {
    private final Amount discountAmount;
    private final int discountPercentage;
    private final String discountType;

    /**
     * Creates a new instance with the specified discount information.
     * @param discountAmount The discount amount.
     * @param discountPercentage The discount percentage.
     * @param discountType The discount type.
     */
    public DiscountInfoDTO(Amount discountAmount, int discountPercentage, String discountType){
        this.discountAmount = new Amount(discountAmount.getAmount());
        this.discountPercentage = discountPercentage;
        this.discountType = discountType;
    }

    /**
     * Creates a new instance with no discount.
     */
    public DiscountInfoDTO(){
        this(new Amount(0), 0, "none");
    }

    /**
     * Get the discount amount.
     * @return A copy of the discount amount.
     */
    public Amount getDiscountAmount(){
        return new Amount(discountAmount.getAmount());
    }

    /**
     * Get the discount percentage.
     * @return The discount percentage.
     */
    public int getDiscountPercentage(){
        return discountPercentage;
    }

    /**
     * Get the discount type.
     * @return The discount type.
     */
    public String getDiscountType(){
        return discountType;
    }

    /**
     * Checks if the discount is applicable (has a non-zero amount or percentage).
     * @return True if the discount is applicable, false otherwise.
     */
    public boolean isApplicable(){
        return discountAmount.getAmount() > 0 || discountPercentage > 0;
    }
}
