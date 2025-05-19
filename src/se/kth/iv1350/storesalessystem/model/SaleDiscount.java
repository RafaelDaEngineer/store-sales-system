package se.kth.iv1350.storesalessystem.model;

import se.kth.iv1350.storesalessystem.integration.dto.DiscountInfoDTO;

import java.util.Objects;

/**
 * Represents a discount applied to a sale.
 * This class encapsulates the logic for managing and applying
 * discounts based on discount information.
 */
public class SaleDiscount {
    private DiscountStrategy discountStrategy;

    /**
     * Default constructor for the SaleDiscount class.
     * Initializes a new instance of the SaleDiscount class with a no-discount strategy.
     */
    public SaleDiscount() {
        this.discountStrategy = new NoDiscountStrategy();
    }

    /**
     * Sets the discount information for the sale.
     * Updates the current discount strategy based on the provided {@code DiscountInfoDTO} instance.
     *
     * @param discountInfo The {@code DiscountInfoDTO} object containing discount-related information,
     *                     including the discount amount, percentage, and type.
     */
    public void setDiscountInfo(DiscountInfoDTO discountInfo) {
        if (!discountInfo.isApplicable()) {
            this.discountStrategy = new NoDiscountStrategy();
            return;
        }

        DiscountStrategy percentageStrategy = null;
        DiscountStrategy fixedStrategy = null;

        if (discountInfo.getDiscountPercentage() > 0) {
            percentageStrategy = new PercentageDiscountStrategy(discountInfo.getDiscountPercentage());
        }

        if (discountInfo.getDiscountAmount().getAmount() > 0) {
            fixedStrategy = new FixedDiscountStrategy(discountInfo.getDiscountAmount());
        }

        if (percentageStrategy != null && fixedStrategy != null) {
            this.discountStrategy = new CombinedDiscountStrategy(percentageStrategy, fixedStrategy);
        } else if (percentageStrategy != null) {
            this.discountStrategy = percentageStrategy;
        } else this.discountStrategy = Objects.requireNonNullElseGet(fixedStrategy, NoDiscountStrategy::new);
    }

    /**
     * Applies the current discount strategy to the given amount.
     *
     * @param amount The {@code Amount} object representing the original monetary value before discount.
     * @return A new {@code Amount} object representing the value after applying the discount.
     */
    public Amount applyDiscountTo(Amount amount) {
        return discountStrategy.applyDiscount(amount);
    }

    /**
     * Gets a description of the current discount strategy.
     *
     * @return A string describing the current discount strategy.
     */
    public String getDiscountDescription() {
        return discountStrategy.getDescription();
    }
}