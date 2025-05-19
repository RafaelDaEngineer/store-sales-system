package se.kth.iv1350.storesalessystem.model;

public interface DiscountStrategy {

    Amount applyDiscount(Amount amount);

    String getDescription();
}
