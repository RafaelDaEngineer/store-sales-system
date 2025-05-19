package se.kth.iv1350.storesalessystem.model;

public class FixedDiscountStrategy implements DiscountStrategy {
    private final Amount discountAmount;

    public FixedDiscountStrategy(Amount discountAmount) {
        this.discountAmount = new Amount(discountAmount.getAmount());
    }

    @Override
    public Amount applyDiscount(Amount amount) {
        if (discountAmount.getAmount() > amount.getAmount()) {
            return new Amount(0.0);
        }
        return amount.minus(discountAmount);
    }

    @Override
    public String getDescription() {
        return "Fixed discount of " + discountAmount.getAmount() + " SEK";
    }
}
