package se.kth.iv1350.storesalessystem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CashRegisterTest {
    private CashRegister cashRegister;

    @BeforeEach
    void setUp() {
        cashRegister = new CashRegister();
    }

    @Test
    void testInitialBalance(){
        assertEquals(0.0, cashRegister.getBalance().getAmount(), "Initial balance should be 0");
    }

    @Test
    void testAddPayment(){
        Amount payment1 = new Amount(100);
        cashRegister.addPayment(payment1);
        assertEquals(100.0, cashRegister.getBalance().getAmount(), "Balance should be 100 after adding payment");

        Amount payment2 = new Amount(50);
        cashRegister.addPayment(payment2);
        assertEquals(150.0, cashRegister.getBalance().getAmount(), "Balance should be cumulative after multiple payments");
    }

    @Test
    void testBalanceIsDefensivlyCopied(){
        Amount payment = new Amount(100);
        cashRegister.addPayment(payment);

        Amount balance = cashRegister.getBalance();
        balance = balance.plus(new Amount(50));

        assertEquals(100.0, cashRegister.getBalance().getAmount(), "Modifying the returned balance should not affect the cash register");
    }
}