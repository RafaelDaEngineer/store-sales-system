package se.kth.iv1350.storesalessystem.model;

import se.kth.iv1350.storesalessystem.integration.dto.DiscountInfoDTO;
import se.kth.iv1350.storesalessystem.model.dto.ReceiptDTO;
import se.kth.iv1350.storesalessystem.model.dto.SaleInfoDTO;

import java.time.LocalTime;

public class Sale {
    private LocalTime saleTime;

    public Sale(){
        this.saleTime = LocalTime.now();
    }

    public void addItem(int ItemID, int quantity){

    }

    public SaleInfoDTO getSaleInfoForDiscount(){

    }

    public void applyDiscount(DiscountInfoDTO discountInfo){

    }

    public Amount getTotalAfterDiscount(){

    }

    public Amount getTotalWithTax(){

    }

    public Amount calculateChange(Amount paidAmount){

    }

    public ReceiptDTO getReceiptDetails(){

    }

    public SaleInfoDTO getSaleInfoForAccounting(){

    }

    public SaleInfoDTO getSaleInfoForInventory(){

    }

    private void setTimeOfSale(){

    }

}
