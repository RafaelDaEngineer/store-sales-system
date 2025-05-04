package se.kth.iv1350.storesalessystem.integration;

import se.kth.iv1350.storesalessystem.model.dto.SaleInfoDTO;

/**
 * Handles interactions with the external accounting system.
 * This class simulates connecting and sending sale information
 * to an accounting system that tracks financial data.
 */
public class AccountingSystem {

    /**
     * Creates a new instance of the AccountingSystem.
     * This constructor initializes the AccountingSystem, allowing it to simulate
     * interactions with an external accounting system that manages financial data.
     */
    public AccountingSystem(){
    }

    /**
     * Updates the accounting system with information about a completed sale.
     * This method simulates the process of connecting to an external accounting
     * system to log financial data for the specified sale.
     *
     * @param saleInfoAccounting The information about the completed sale, including
     *                           details such as running total, customer ID, and total VAT.
     */
    public void updateAccounting(SaleInfoDTO saleInfoAccounting){
    }
}
