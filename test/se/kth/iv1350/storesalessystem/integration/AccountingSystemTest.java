package se.kth.iv1350.storesalessystem.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.storesalessystem.integration.dto.ItemDTO;
import se.kth.iv1350.storesalessystem.model.Amount;
import se.kth.iv1350.storesalessystem.model.dto.SaleInfoDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountingSystemTest {
    private AccountingSystem accountingSystem;
    private SaleInfoDTO saleInfo;

    @BeforeEach
    void setUp(){
        accountingSystem = new AccountingSystem();

        List<ItemDTO> items = new ArrayList<>();
        items.add(new ItemDTO("1", "Test Item", "Description", 0.25, new Amount(100)));
        saleInfo = new SaleInfoDTO(1, new Amount(100), items, 12345, new Amount(25));
    }

    @Test
    void testUpdateAccounting(){
        // Since the implementation is empty, we can only test that it doesn't throw exceptions
        assertDoesNotThrow(() -> accountingSystem.updateAccounting(saleInfo),
                "Update accounting should not throw exceptions");
    }

}