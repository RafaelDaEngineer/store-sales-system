package se.kth.iv1350.storesalessystem.model;

/**
 * Represents a monetary amount in the sales system.
 */
public class Amount {
    private double amount;
    
    /**
     * Creates a new instance with the specified amount.
     * 
     * @param amount The amount represented by this object.
     */
    public Amount(double amount) {
        this.amount = amount;
    }
    
    /**
     * Creates a new instance with zero amount.
     */
    public Amount() {
        this(0);
    }
    
    /**
     * Returns the current amount.
     * 
     * @return The amount stored in this object.
     */
    public double getAmount() {
        return amount;
    }
    
    /**
     * Adds the specified amount to this object.
     * 
     * @param other The amount to add.
     * @return A new Amount object containing the sum.
     */
    public Amount plus(Amount other) {
        return new Amount(this.amount + other.amount);
    }
    
    /**
     * Subtracts the specified amount from this object.
     * 
     * @param other The amount to subtract.
     * @return A new Amount object containing the difference.
     */
    public Amount minus(Amount other) {
        return new Amount(this.amount - other.amount);
    }
    
    /**
     * Multiplies this amount by the specified factor.
     * 
     * @param factor The factor to multiply by.
     * @return A new Amount object with the multiplied value.
     */
    public Amount multiply(double factor) {
        return new Amount(this.amount * factor);
    }
}