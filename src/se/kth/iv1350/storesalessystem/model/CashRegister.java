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

    /**
     * Adds an observer to the list of observers that are notified whenever a significant event occurs,
     * such as the total revenue being updated. Observers implement the TotalRevenueObserver interface.
     *
     * @param observer The observer to be added to the notification list.
     */
    public void addObserver(TotalRevenueObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the list of observers. The observer will no longer be
     * notified of updates or changes related to total revenue.
     *
     * @param observer The observer to be removed from the notification list.
     */
    public void removeObserver(TotalRevenueObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all registered observers of a new payment. Each observer's {@code newPayment}
     * method is called with the specified payment amount.
     *
     * @param payment The payment amount that has been processed and is to be notified to the observers.
     */
    private void notifyObservers(Amount payment) {
        for (TotalRevenueObserver observer : observers) {
            observer.newPayment(payment);
        }
    }
}
