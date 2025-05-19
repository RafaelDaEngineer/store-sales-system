package se.kth.iv1350.storesalessystem.model;

/**
 * Represents a discount strategy that combines two individual discount strategies.
 * The combined strategy applies the first discount strategy to a given amount
 * and then applies the second discount strategy to the resultant amount.
 * This allows for flexible composition of multiple discounting behaviors.
 * Both discount strategies must be provided during instantiation of this class.
 * <p>
 * This class implements the {@code DiscountStrategy} interface, which means it provides a
 * concrete implementation for applying discounts and retrieving a description of the strategy.
 */
public class CombinedDiscountStrategy implements DiscountStrategy {
    private final DiscountStrategy firstDiscount;
    private final DiscountStrategy secondDiscount;

    /**
     * Constructs a CombinedDiscountStrategy using two individual discount strategies.
     * The first discount strategy is applied to the given amount, and the second
     * discount strategy is applied to the result of the first.
     *
     * @param firstDiscount  The first discount strategy to be applied. Must not be null.
     * @param secondDiscount The second discount strategy to be applied after the first. Must not be null.
     */
    public CombinedDiscountStrategy(DiscountStrategy firstDiscount, DiscountStrategy secondDiscount) {
        this.firstDiscount = firstDiscount;
        this.secondDiscount = secondDiscount;
    }

    /**
     * Applies a combined discount to the specified monetary amount by first applying
     * the first discount strategy and then applying the second discount strategy
     * to the result of the first.
     *
     * @param amount The original monetary amount to which the discounts will be applied.
     *               Must not be null and should represent a non-negative value.
     * @return A new {@code Amount} object representing the monetary amount after
     * both discounts have been applied.
     */
    @Override
    public Amount applyDiscount(Amount amount) {
        Amount afterFirstDiscount = firstDiscount.applyDiscount(amount);
        return secondDiscount.applyDiscount(afterFirstDiscount);
    }

    /**
     * Provides a description of the combined discount strategy, which includes
     * the description of the first discount strategy and the second discount strategy.
     *
     * @return A string describing the combined discount strategy. The format combines
     * the descriptions of the two individual discount strategies.
     */
    @Override
    public String getDescription() {
        return "Combined discount: " + firstDiscount.getDescription() + " and " + secondDiscount.getDescription();
    }
}
