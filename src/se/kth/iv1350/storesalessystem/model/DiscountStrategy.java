package se.kth.iv1350.storesalessystem.model;

/**
 * Defines a strategy for applying a discount to a monetary amount.
 * Implementations of this interface provide specific discounting behaviors,
 * such as fixed discounts, percentage discounts, or combined discount strategies.
 */
public interface DiscountStrategy {

    /**
     * Applies a discount to the specified monetary amount and returns the adjusted amount.
     * The specific discounting behavior is determined by the implementation.
     *
     * @param amount The original monetary amount to which the discount will be applied.
     *               Must not be null and should represent a non-negative value.
     * @return A new {@code Amount} object representing the monetary amount after
     * the discount has been applied.
     */
    Amount applyDiscount(Amount amount);

    /**
     * Provides a description of the specific discount strategy being used.
     *
     * @return A string that describes the discount strategy.
     */
    String getDescription();
}
