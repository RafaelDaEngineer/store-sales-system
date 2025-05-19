package se.kth.iv1350.storesalessystem.model;

public class NoDiscountStrategy implements DiscountStrategy {

    /**
     * Applies a discount to the specified monetary amount.
     * In this implementation, no discount is applied, and the original amount is returned.
     *
     * @param amount The original monetary amount to which the discount is to be applied.
     * @return A new Amount object representing the same monetary value as the input.
     */
    @Override
    public Amount applyDiscount(Amount amount) {
        return new Amount(amount.getAmount());
    }

    /**
     * Provides a description of the current discount strategy.
     *
     * @return A string describing the discount strategy, specifically
     * "No discount" for this implementation.
     */
    @Override
    public String getDescription() {
        return "No discount";
    }
}
