package se.kth.iv1350.storesalessystem.model.dto;

import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.Amount;

public class SaleInfoDTO {
    private Amount runningTotal;
    private int saleID;
    private ItemDTO items;
    private int customerID;
    private Amount totalVAT;

    public SaleInfoDTO(){

    }
}
