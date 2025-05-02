package se.kth.iv1350.storesalessystem.integration.dto;

import se.kth.iv1350.storesalessystem.model.Amount;

public class ItemDTO {
    private String description;
    private double tax;
    private Amount price;
    private int itemID;

    public ItemDTO(int itemID) {

    }
}
