package se.kth.iv1350.storesalessystem.integration;

import se.kth.iv1350.storesalessystem.integration.dto.DiscountInfoDTO;
import se.kth.iv1350.storesalessystem.model.dto.SaleInfoDTO;


/**
 * Handles discount information retrieval from an external discount database.
 */
public class DiscountDatabase {

    /**
     * Creates a new instance.
     */
    public DiscountDatabase(){
    }

    /**
     * Retrieves discount information for a specific customer and sale.
     *
     * @param customerID The ID of the customer to get discount for.
     * @param saleInfo Information about the current sale.
     * @return Discount information applicable to this customer and sale.
     */
    public DiscountInfoDTO findDiscount(int customerID, SaleInfoDTO saleInfo){
        // Implmentation would connect to an external discount database and retrieve applicable discount information.
        //This is a placeholder for the real implementation.
        return new DiscountInfoDTO();
    }
}
