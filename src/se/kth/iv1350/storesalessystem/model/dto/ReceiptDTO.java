package se.kth.iv1350.storesalessystem.model.dto;

import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.Amount;

public class ReceiptDTO {
    private String dateTime;
    private ItemDTO items;
    private Amount totalAmount;
    private Amount totalVAT;
    private Amount totalPaid;
    private Amount change;

    public ReceiptDTO(){

    }
}
