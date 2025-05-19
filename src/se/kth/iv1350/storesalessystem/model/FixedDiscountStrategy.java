package se.kth.iv1350.storesalessystem.model;

/**
 * Represents a discount strategy that applies a fixed discount amount
 * to an initial monetary amount.
 * This class implements the DiscountStrategy interface and provides
 * methods to calculate the discounted amount and to retrieve a description
 * of the discount strategy.
 */
public class FixedDiscountStrategy implements DiscountStrategy {
    private final Amount discountAmount;

    /**
     * Constructs a FixedDiscountStrategy with a specified fixed discount amount.
     *
     * @param discountAmount The fixed discount amount to be applied.
     *                       It must be an instance of the Amount class.
     */
    public FixedDiscountStrategy(Amount discountAmount) {
        this.discountAmount = new Amount(discountAmount.getAmount());
    }

    /**
     * Applies a fixed discount to the given monetary amount. If the discount amount is greater than
     * the given amount, the result will be zero.
     *
     * @param amount The original monetary amount to which the discount will be applied.
     * @return A new Amount object representing the discounted value. If the discount exceeds the
     * original amount, the returned amount will be 0.
     */
    @Override
    public Amount applyDiscount(Amount amount) {
        if (discountAmount.getAmount() > amount.getAmount()) {
            return new Amount(0.0);
        }
        return amount.minus(discountAmount);
    }

    /**
     * Provides a description of the fixed discount strategy.
     *
     * @return A string describing the fixed discount, including the discount amount and its currency in SEK.
     */
    @Override
    public String getDescription() {
        return "Fixed discount of " + discountAmount.getAmount() + " SEK";
    }
}
