package se.kth.iv1350.storesalessystem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.storesalessystem.integration.dto.DiscountInfoDTO;

import static org.junit.jupiter.api.Assertions.*;

class SaleDiscountTest {
    private SaleDiscount saleDiscount;

    @BeforeEach
    void setUp(){
        saleDiscount = new SaleDiscount();
    }

    @Test
    void testDefaultDiscountIsNone(){
        Amount amount = new Amount(100);
        Amount afterDiscount = saleDiscount.applyDiscountTo(amount);

        assertEquals(amount.getAmount(), afterDiscount.getAmount(), "With no discount applied, amount should remain the same");
    }

    @Test
    void testFixedDiscount(){
        DiscountInfoDTO discountInfo = new DiscountInfoDTO(new Amount(20), 0, "Fixed Discount");
        saleDiscount.setDiscountInfo(discountInfo);

        Amount amount = new Amount(100);
        Amount afterDiscount = saleDiscount.applyDiscountTo(amount);

        assertEquals(amount.getAmount() - 20, afterDiscount.getAmount(), "Fixed discount should be applied correctly");
    }

    @Test
    void testPercentageDiscount(){
        DiscountInfoDTO discountInfo = new DiscountInfoDTO(new Amount(0), 15, "Percentage Discount");
        saleDiscount.setDiscountInfo(discountInfo);

        Amount amount = new Amount(100);
        Amount afterDiscount = saleDiscount.applyDiscountTo(amount);

        assertEquals(amount.getAmount() - (amount.getAmount() * 0.15), afterDiscount.getAmount(), "Percentage discount should be applied correctly");
    }

    @Test
    void testCombinedDiscount(){
        DiscountInfoDTO discountInfo = new DiscountInfoDTO(new Amount(10), 10, "Combined Discount");
        saleDiscount.setDiscountInfo(discountInfo);

        Amount amount = new Amount(100);
        Amount afterDiscount = saleDiscount.applyDiscountTo(amount);

        assertEquals(amount.getAmount() - (amount.getAmount() * 0.1) - 10, afterDiscount.getAmount(), "Combined discount should be applied correctly");
    }

    @Test
    void testDiscountCannotExceedAmount(){
        DiscountInfoDTO discountInfo = new DiscountInfoDTO(new Amount(200), 50, "Large Discount");
        saleDiscount.setDiscountInfo(discountInfo);

        Amount amount = new Amount(100);
        Amount afterDiscount = saleDiscount.applyDiscountTo(amount);

        assertEquals(0.0, afterDiscount.getAmount(), "Discount cannot exceed original amount");
    }
}