package se.kth.iv1350.storesalessystem.model;

/**
 * Represents an immutable monetary amount with basic arithmetic operations.
 */
public class Amount {
    private final double amount;
    
    /**
     * Creates a new instance with the specified amount.
     * 
     * @param amount The amount represented by this object.
     */
    public Amount(double amount) {
        this.amount = amount;
    }

    /**
     * Creates a new instance of the Amount class with a default value of 0.
     */
    public Amount() {
        this(0);
    }

    /**
     * Retrieves the monetary amount represented by this object.
     *
     * @return The monetary amount as a double value.
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