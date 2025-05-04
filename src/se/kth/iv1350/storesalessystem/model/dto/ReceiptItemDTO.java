package se.kth.iv1350.storesalessystem.model.dto;

import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.Amount;

public class ReceiptItemDTO {
    private final ItemDTO item;
    private final int quantity;

    public ReceiptItemDTO(ItemDTO item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }

    public ItemDTO getItem(){
        return item;
    }

    public int getQuantity(){
        return quantity;
    }

    public Amount getTotalPrice(){
        return item.getPrice().multiply(quantity);
    }
}
