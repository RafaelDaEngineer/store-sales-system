package se.kth.iv1350.storesalessystem.model;

public class NoDiscountStrategy implements DiscountStrategy {

    @Override
    public Amount applyDiscount(Amount amount) {
        return new Amount(amount.getAmount());
    }

    @Override
    public String getDescription() {
        return "No discount";
    }
}
