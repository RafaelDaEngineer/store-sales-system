package se.kth.iv1350.storesalessystem.model;


/**
 * A discount strategy that applies a percentage-based discount to a given amount.
 * This class implements the DiscountStrategy interface to provide percentage-based discounting functionality.
 */
public class PercentageDiscountStrategy implements DiscountStrategy {
    private final int discountPercentage;

    /**
     * Constructs a new instance of PercentageDiscountStrategy with the specified discount percentage.
     * The discount percentage represents the percentage of the original amount to be deducted.
     *
     * @param discountPercentage The percentage value of the discount to be applied.
     *                           It should be a non-negative integer value.
     */
    public PercentageDiscountStrategy(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    /**
     * Applies a percentage-based discount to the specified amount.
     * The discount is calculated based on the discount percentage defined in the strategy.
     *
     * @param amount The original amount to which the discount will be applied.
     * @return A new Amount object representing the discounted value.
     */
    @Override
    public Amount applyDiscount(Amount amount) {
        double discountRate = discountPercentage / 100.0;
        Amount discount = amount.multiply(discountRate);
        return amount.minus(discount);
    }

    /**
     * Provides a textual description of the current discount percentage.
     *
     * @return A string describing the percentage discount, formatted as
     * "<discount>% discount".
     */
    @Override
    public String getDescription() {
        return discountPercentage + "% discount";
    }
}
