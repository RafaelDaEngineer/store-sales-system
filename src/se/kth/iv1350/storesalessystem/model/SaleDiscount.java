package se.kth.iv1350.storesalessystem.model;

import se.kth.iv1350.storesalessystem.integration.dto.DiscountInfoDTO;

/**
 * Represents a discount applied to a sale.
 * This class encapsulates the logic for managing and applying
 * discounts based on discount information.
 */
public class SaleDiscount {
    private DiscountInfoDTO discountInfo;

    /**
     * Default constructor for the SaleDiscount class.
     * Initializes a new instance of the SaleDiscount class with default discount information.
     * Creates a new instance of DiscountInfoDTO with default values to represent no discount applied.
     */
    public SaleDiscount(){
        this.discountInfo = new DiscountInfoDTO();
    }

    /**
     * Sets the discount information for the sale.
     * Updates the current discount details with the provided {@code DiscountInfoDTO} instance.
     *
     * @param discountInfo The {@code DiscountInfoDTO} object containing discount-related information,
     *                     including the discount amount, percentage, and type.
     */
    public void setDiscountInfo(DiscountInfoDTO discountInfo){
        this.discountInfo = discountInfo;
    }

    /**
     * Applies the discount defined in the {@code DiscountInfoDTO} object to the given amount.
     * Discounts may consist of a fixed amount, a percentage, or a combination of both.
     * If the total discount exceeds the original amount, the resulting amount is set to zero.
     *
     * @param amount The {@code Amount} object representing the original monetary value before discount.
     * @return A new {@code Amount} object representing the value after applying the discount.
     */
    public Amount applyDiscountTo(Amount amount){
        if(!discountInfo.isApplicable()){
            return new Amount(amount.getAmount());
        }

        Amount fixedDiscount = discountInfo.getDiscountAmount();

        double percentageDiscountRate = discountInfo.getDiscountPercentage() / 100.0;
        Amount percentageDiscount = amount.multiply(percentageDiscountRate);

        Amount totalDiscount = fixedDiscount.plus(percentageDiscount);

        if(totalDiscount.getAmount() > amount.getAmount()){
            totalDiscount = new Amount(amount.getAmount());
        }

        return amount.minus(totalDiscount);
    }
}
