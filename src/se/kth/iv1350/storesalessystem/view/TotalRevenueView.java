package se.kth.iv1350.storesalessystem.view;

import se.kth.iv1350.storesalessystem.model.Amount;
import se.kth.iv1350.storesalessystem.model.TotalRevenueObserver;

/**
 * A view class that observes total revenue updates and displays the cumulative revenue
 * to the standard output whenever a new payment is processed.
 * This class implements the TotalRevenueObserver interface and listens for updates
 * to the total revenue. It prints the current total revenue to the console after
 * formatting the amount.
 */
public class TotalRevenueView implements TotalRevenueObserver {
    private Amount totalRevenue;

    /**
     * Creates a new instance of the TotalRevenueView class.
     * This constructor initializes the total revenue to a default value of zero.
     * The total revenue represents the cumulative monetary amount collected
     * since the start of the program. TotalRevenueView acts as an observer
     * of total revenue updates, displaying the cumulative revenue to the
     * standard output whenever a new payment is processed.
     */
    public TotalRevenueView() {
        this.totalRevenue = new Amount();
    }

    /**
     * Updates the total revenue by adding the specified payment amount.
     * This method is invoked when a new payment is processed, and it ensures
     * that the cumulative revenue is updated and displayed to the standard output.
     *
     * @param payment The payment amount to be added to the total revenue.
     */
    @Override
    public void newPayment(Amount payment) {
        totalRevenue = totalRevenue.plus(payment);
        printCurrentRevenue();
    }

    /**
     * Prints the current total revenue to the standard output.
     * This method retrieves the stored total revenue, formats it as a
     * string in a localized currency format, and outputs it to the console.
     * The total revenue represents the cumulative revenue collected since
     * the program's start.
     */
    private void printCurrentRevenue() {
        System.out.println("Total revenue since program start: " + formatAmount(totalRevenue.getAmount()) + " SEK");
    }

    private String formatAmount(double amount) {
        return String.format("%.2f", amount).replace(".", ":") + " SEK";
    }
}
