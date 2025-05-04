package se.kth.iv1350.storesalessystem.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountTest {

    @Test
    void testConstructorWithValue(){
        Amount amount = new Amount(100.0);
        assertEquals(100.0,amount.getAmount(), "Amount should store the value passed to the constructor");
    }

    @Test
    void testDefaultConstructor(){
        Amount amount = new Amount();
        assertEquals(0.0,amount.getAmount(), "Amount should be 0 by default");
    }

    @Test
    void testPlus(){
        Amount amount1 = new Amount(100.0);
        Amount amount2 = new Amount(50.0);

        Amount result = amount1.plus(amount2);

        assertEquals(150.0, result.getAmount(), "Amount should be 150 after adding two amounts");
        assertEquals(100.0, amount1.getAmount(), "Original amount should not be modified");
        assertEquals(50.0, amount2.getAmount(), "Original amount should not be modified");
    }

    @Test
    void testMinus(){
        Amount amount1 = new Amount(100.0);
        Amount amount2 = new Amount(30.0);

        Amount result = amount1.minus(amount2);

        assertEquals(70.0, result.getAmount(), "Amount should be 70 after subtracting two amounts");
        assertEquals(100.0, amount1.getAmount(), "Original amount should not be modified");
        assertEquals(30.0, amount2.getAmount(), "Original amount should not be modified");
    }

    @Test
    void testMultiply(){
        Amount amount1 = new Amount(100.0);

        Amount result = amount1.multiply(1.5);

        assertEquals(150.0, result.getAmount(), "Amount should be 150 after multiplying by 1.5");
        assertEquals(100.0, amount1.getAmount(), "Original amount should not be modified");
    }
}