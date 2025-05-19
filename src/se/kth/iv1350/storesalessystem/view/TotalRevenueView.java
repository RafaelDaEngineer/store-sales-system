package se.kth.iv1350.storesalessystem.view;

import se.kth.iv1350.storesalessystem.model.Amount;
import se.kth.iv1350.storesalessystem.model.TotalRevenueObserver;

public class TotalRevenueView implements TotalRevenueObserver {
    private Amount totalRevenue;

    public TotalRevenueView() {
        this.totalRevenue = new Amount();
    }

    @Override
    public void newPayment(Amount payment) {
        totalRevenue = totalRevenue.plus(payment);
        printCurrentRevenue();
    }

    private void printCurrentRevenue() {
        System.out.println("Total revenue since program start: " + formatAmount(totalRevenue.getAmount()) + " SEK");
    }

    private String formatAmount(double amount) {
        return String.format("%.2f", amount).replace(".", ":") + " SEK";
    }
}
