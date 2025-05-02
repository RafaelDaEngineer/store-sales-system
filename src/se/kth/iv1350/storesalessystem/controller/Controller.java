package se.kth.iv1350.storesalessystem.controller;

import se.kth.iv1350.storesalessystem.integration.AccountingSystem;
import se.kth.iv1350.storesalessystem.integration.DiscountDatabase;
import se.kth.iv1350.storesalessystem.integration.InventorySystem;
import se.kth.iv1350.storesalessystem.integration.Printer;
import se.kth.iv1350.storesalessystem.model.Amount;

public class Controller {
    public Controller(InventorySystem inventorySystem, DiscountDatabase discountDatabase, AccountingSystem accountingSystem, Printer printer){

    }

    public void startSale(){
    }

    public void enterItem(int quantity, int itemID){

    }

    public void requestDiscount(int customerID){

    }

    public void endSale(){
    }

    public void makePayment(Amount paidAmount){

    }
}
