package se.kth.iv1350.storesalessystem.integration;

import se.kth.iv1350.storesalessystem.integration.dto.DiscountInfoDTO;
import se.kth.iv1350.storesalessystem.model.Amount;
import se.kth.iv1350.storesalessystem.model.dto.SaleInfoDTO;


/**
 * Handles retrieval of discount information from an external discount database.
 * This class is responsible for providing discount details applicable to specific
 * customers and sales, based on customer and sale data.
 * This class implements the Singleton pattern to ensure only one instance exists.
 */
public class DiscountDatabase {

    private static DiscountDatabase instance;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * Part of the Singleton pattern implementation.
     */
    private DiscountDatabase() {

    }

    /**
     * Gets the single instance of the DiscountDatabase.
     * If no instance exists, one is created.
     *
     * @return The singleton instance of DiscountDatabase
     */
    public static synchronized DiscountDatabase getInstance() {
        if (instance == null) {
            instance = new DiscountDatabase();
        }
        return instance;
    }

    /**
     * Retrieves discount information applicable to a specific customer and their ongoing sale.
     * The method interacts with an external database to fetch the discount details based
     * on customer ID and sale information. This is a placeholder for the real implementation.
     *
     * @param customerID The unique identifier of the customer for whom the discount is being retrieved.
     * @param saleInfo   The data transfer object containing details about the current sale,
     *                   including total amount, VAT, and customer information.
     * @return A {@code DiscountInfoDTO} object containing the applicable discount details for the specified customer and sale.
     */
    public DiscountInfoDTO findDiscount(int customerID, SaleInfoDTO saleInfo) {

        if (customerID >= 10000 && customerID < 20000) {
            return new DiscountInfoDTO(new Amount(0), 10, "Percentage Discount");
        }

        if (customerID >= 20000 && customerID < 30000) {
            return new DiscountInfoDTO(new Amount(50), 0, "Fixed Discount");
        }

        if (customerID >= 30000 && customerID < 40000) {
            return new DiscountInfoDTO(new Amount(25), 5, "Premium Discount");
        }

        return new DiscountInfoDTO();
    }
}
