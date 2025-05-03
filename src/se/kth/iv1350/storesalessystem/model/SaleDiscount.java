package se.kth.iv1350.storesalessystem.model;

import se.kth.iv1350.storesalessystem.integration.dto.DiscountInfoDTO;

/**
 * Handles discount calculation for a sale.
 */
public class SaleDiscount {
    private DiscountInfoDTO discountInfo;

    /**
     * Creates a new instance with no discount.
     */
    public SaleDiscount(){
        this.discountInfo = new DiscountInfoDTO();
    }

    /**
     * Sets the discount information for the sale.
     *
     * @param discountInfo The discount to apply to the sale.
     */
    public void setDiscountInfo(DiscountInfoDTO discountInfo){
        this.discountInfo = discountInfo;
    }

    /**
     * Applies the current discount to the specified amount.
     * @param amount The amount to apply the discount to.
     * @return The amount after discount has been applied.
     */
    public Amount applyDiscountTo(Amount amount){
        if(discountInfo.isApplicable()){
            Amount discountedAmount = discountInfo.calculateDiscountValue(amount);
            return amount.minus(discountedAmount);
        }
        return new Amount(amount.getAmount());
    }

    /**
     * Gets the current discount information.
     *
     * @return The discount information.
     */
    public DiscountInfoDTO getDiscountInfo(){
        return discountInfo;
    }
}
