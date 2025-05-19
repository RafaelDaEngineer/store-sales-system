package se.kth.iv1350.storesalessystem.model;


public class PercentageDiscountStrategy implements DiscountStrategy {
    private final int discountPercentage;

    public PercentageDiscountStrategy(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public Amount applyDiscount(Amount amount) {
        double discountRate = discountPercentage / 100.0;
        Amount discount = amount.multiply(discountRate);
        return amount.minus(discount);
    }

    @Override
    public String getDescription() {
        return discountPercentage + "% discount";
    }
}
