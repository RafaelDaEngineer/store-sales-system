package se.kth.iv1350.storesalessystem.model;

public class CashRegister {
    private Amount balance;

    public CashRegister(){
        this.balance = new Amount();
    }

    public void addPayment(Amount payemnt) {
        this.balance = balance.plus(payemnt);
    }

    public Amount getBalance() {
        return new Amount(balance.getAmount());
    }
}
