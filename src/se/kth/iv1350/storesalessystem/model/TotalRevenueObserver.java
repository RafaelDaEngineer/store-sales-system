package se.kth.iv1350.storesalessystem.model;


/**
 * An observer interface for monitoring total revenue updates in a retail sales system.
 * Implementations of this interface will be notified whenever the total revenue changes.
 */
public interface TotalRevenueObserver {

    /**
     * Notifies this observer about a new payment that has been processed.
     * The observer can use this information to update its internal state or perform specific actions.
     *
     * @param payment The payment amount that has been processed.
     */
    void newPayment(Amount payment);
}
