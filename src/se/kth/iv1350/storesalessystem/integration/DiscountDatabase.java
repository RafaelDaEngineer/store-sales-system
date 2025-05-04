package se.kth.iv1350.storesalessystem.integration;

import se.kth.iv1350.storesalessystem.integration.dto.DiscountInfoDTO;
import se.kth.iv1350.storesalessystem.model.dto.SaleInfoDTO;


/**
 * Handles retrieval of discount information from an external discount database.
 * This class is responsible for providing discount details applicable to specific
 * customers and sales, based on customer and sale data.
 */
public class DiscountDatabase {

    /**
     * Creates a new instance.
     */
    public DiscountDatabase(){
    }

    /**
     * Retrieves discount information applicable to a specific customer and their ongoing sale.
     * The method interacts with an external database to fetch the discount details based
     * on customer ID and sale information. This is a placeholder for the real implementation.
     *
     * @param customerID The unique identifier of the customer for whom the discount is being retrieved.
     * @param saleInfo The data transfer object containing details about the current sale,
     *                 including total amount, VAT, and customer information.
     * @return A {@code DiscountInfoDTO} object containing the applicable discount details for the specified customer and sale.
     */
    public DiscountInfoDTO findDiscount(int customerID, SaleInfoDTO saleInfo){
        return new DiscountInfoDTO();
    }
}
