package se.kth.iv1350.storesalessystem.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a cash register for handling payments and maintaining a running balance.
 */
public class CashRegister {
    private Amount balance;
    private final List<TotalRevenueObserver> observers = new ArrayList<>();

    /**
     * Creates a new instance of the CashRegister class.
     * Initializes the cash register with a balance of zero.
     */
    public CashRegister() {
        this.balance = new Amount();
    }

    /**
     * Adds a payment to the current balance of the cash register.
     *
     * @param payment The payment amount to be added to the balance.
     */
    public void addPayment(Amount payment) {
        this.balance = balance.plus(payment);
        notifyObservers(payment);
    }

    /**
     * Retrieves the current balance of this cash register.
     *
     * @return The current balance as an Amount object.
     */
    public Amount getBalance() {
        return new Amount(balance.getAmount());
    }

    public void addObserver(TotalRevenueObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TotalRevenueObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(Amount payment) {
        for (TotalRevenueObserver observer : observers) {
            observer.newPayment(payment);
        }
    }
}
