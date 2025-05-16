package se.kth.iv1350.storesalessystem.integration.dto;

import se.kth.iv1350.storesalessystem.model.Amount;

/**
 * Data Transfer Object (DTO) for transferring discount information within the sales system.
 * This class encapsulates information related to a discount, such as the discount amount,
 * percentage, and type, ensuring immutability and safe encapsulation.
 */
public record DiscountInfoDTO(Amount discountAmount, int discountPercentage, String discountType) {

    /**
     * Creates a new instance of DiscountInfoDTO with the specified discount amount, percentage, and type.
     *
     * @param discountAmount     The monetary value of the discount. This is represented as an {@code Amount} object.
     * @param discountPercentage The percentage value of the discount, represented as an integer.
     * @param discountType       The type of the discount, represented as a string.
     */
    public DiscountInfoDTO(Amount discountAmount, int discountPercentage, String discountType) {
        this.discountAmount = new Amount(discountAmount.getAmount());
        this.discountPercentage = discountPercentage;
        this.discountType = discountType;
    }

    /**
     * Creates a new instance of DiscountInfoDTO with default values.
     * The default values are:
     * - A discount amount of zero, represented by an {@code Amount} object.
     * - A discount percentage of zero.
     * - A discount type of "none".
     * This constructor is used when no specific discount information is provided or available.
     */
    public DiscountInfoDTO() {
        this(new Amount(0), 0, "none");
    }

    /**
     * Retrieves the fixed discount amount encapsulated in this object.
     * This amount represents a specific monetary value for the discount.
     *
     * @return A new {@code Amount} object containing the fixed discount value.
     */
    public Amount getDiscountAmount() {
        return new Amount(discountAmount.getAmount());
    }

    /**
     * Retrieves the discount percentage associated with this discount.
     * This percentage represents the portion of a value that will
     * be discounted when the discount is applied.
     *
     * @return The discount percentage as an integer.
     */
    public int getDiscountPercentage() {
        return discountPercentage;
    }

    /**
     * Retrieves the type of the discount encapsulated in this object.
     * The discount type typically describes the category or nature of the discount.
     *
     * @return The type of the discount as a string.
     */
    public String getDiscountType() {
        return discountType;
    }

    /**
     * Determines if the discount is applicable based on its attributes.
     * A discount is deemed applicable if either its fixed discount amount is greater than zero
     * or if its discount percentage is greater than zero.
     *
     * @return {@code true} if the discount has a positive monetary amount or a positive percentage,
     * {@code false} otherwise.
     */
    public boolean isApplicable() {
        return discountAmount.getAmount() > 0 || discountPercentage > 0;
    }
}
