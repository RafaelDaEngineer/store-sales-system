package se.kth.iv1350.storesalessystem.view;

import se.kth.iv1350.storesalessystem.model.Amount;
import se.kth.iv1350.storesalessystem.model.TotalRevenueObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

/**
 * Handles logging of total revenue to a file. This class observes payments made in the system
 * and keeps track of the total revenue, which is logged to a specified file upon updates.
 * Implements the {@link TotalRevenueObserver} interface for receiving payment notifications.
 */
public class TotalRevenueFileOutput implements TotalRevenueObserver {
    private static final String REVENUE_LOG_FILE = "revenue.txt";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Amount totalRevenue;
    private PrintWriter logStream;

    /**
     * Creates a new instance of {@code TotalRevenueFileOutput} and initializes logging to a file.
     * This constructor initializes the total revenue to zero and attempts to create or open a log file
     * for appending revenue data. The log file is named as specified by the constant {@code REVENUE_LOG_FILE}.
     * If the file cannot be created or opened, an error message is printed to the standard error stream.
     */
    public TotalRevenueFileOutput() {
        this.totalRevenue = new Amount();
        try {
            logStream = new PrintWriter(new FileWriter(REVENUE_LOG_FILE, true));
        } catch (IOException e) {
            System.err.println("ERROR: Could not create or open the log file: " + e.getMessage());
        }
    }

    /**
     * Updates the total revenue with the specified payment and logs the current revenue to the file.
     *
     * @param payment The payment amount to add to the total revenue.
     */
    @Override
    public void newPayment(Amount payment) {
        totalRevenue = totalRevenue.plus(payment);
        logCurrentRevenue();
    }

    /**
     * Logs the current total revenue along with a timestamp to the specified log file.
     * This method formats the current timestamp and the total revenue as a log entry and writes it
     * to the log file. If the log file is not available (indicated by a null stream), the method exits
     * without performing any logging. The log file is flushed after writing the entries.
     * The log entry includes:
     * - A timestamp in the format "yyyy-MM-dd HH:mm:ss".
     * - The total revenue formatted to two decimal places, with the SEK currency symbol.
     * Logging is skipped if the output stream for the log file is not initialized.
     */
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
