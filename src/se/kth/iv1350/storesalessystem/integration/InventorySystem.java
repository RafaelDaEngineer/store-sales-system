package se.kth.iv1350.storesalessystem.integration;

import java.util.HashMap;
import java.util.Map;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.Amount;
import se.kth.iv1350.storesalessystem.model.dto.SaleInfoDTO;

public class InventorySystem {
    private Map<String, ItemDTO> inventory;

    public InventorySystem(){
        this.inventory = new HashMap<>();
    }

    public ItemDTO getItemInfo(String itemID){
        return inventory.get(itemID);
    }

    public void addItem(ItemDTO item) {
        inventory.put(item.getItemID(), item);
    }

    public void updateInventory(SaleInfoDTO saleInfoDTO){

    }
}
