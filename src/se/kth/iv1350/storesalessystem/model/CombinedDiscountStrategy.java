package se.kth.iv1350.storesalessystem.model;

public class CombinedDiscountStrategy implements DiscountStrategy {
    private final DiscountStrategy firstDiscount;
    private final DiscountStrategy secondDiscount;

    public CombinedDiscountStrategy(DiscountStrategy firstDiscount, DiscountStrategy secondDiscount) {
        this.firstDiscount = firstDiscount;
        this.secondDiscount = secondDiscount;
    }

    @Override
    public Amount applyDiscount(Amount amount) {
        Amount afterFirstDiscount = firstDiscount.applyDiscount(amount);
        return secondDiscount.applyDiscount(afterFirstDiscount);
    }

    @Override
    public String getDescription() {
        return "Combined discount: " + firstDiscount.getDescription() + " and " + secondDiscount.getDescription();
    }
}
