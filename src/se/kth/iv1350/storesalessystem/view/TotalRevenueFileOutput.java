package se.kth.iv1350.storesalessystem.view;

import se.kth.iv1350.storesalessystem.model.Amount;
import se.kth.iv1350.storesalessystem.model.TotalRevenueObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

public class TotalRevenueFileOutput implements TotalRevenueObserver {
    private static final String REVENUE_LOG_FILE = "revenue.txt";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Amount totalRevenue;
    private PrintWriter logStream;

    public TotalRevenueFileOutput() {
        this.totalRevenue = new Amount();
        try {
            logStream = new PrintWriter(new FileWriter(REVENUE_LOG_FILE, true));
        } catch (IOException e) {
            System.err.println("ERROR: Could not create or open the log file: " + e.getMessage());
        }
    }

    @Override
    public void newPayment(Amount payment) {
        totalRevenue = totalRevenue.plus(payment);
        logCurrentRevenue();
    }

    private void logCurrentRevenue() {
        if (logStream == null) {
            return;
        }

        String timestamp = TIME_FORMATTER.format(java.time.LocalDateTime.now());

        logStream.println(timestamp + " - Total Revenue: " + String.format(("%.2f"), totalRevenue.getAmount()) + " SEK");
        logStream.println();
        logStream.flush();
    }
}
