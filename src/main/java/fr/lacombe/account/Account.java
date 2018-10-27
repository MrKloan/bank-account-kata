package fr.lacombe.account;

class Account {

    private final long balance;

    private Account(final long balance) {
        this.balance = balance;
    }

    static Account of(final long balance) {
        return new Account(balance);
    }

    long deposit(final long amount) {
        if(balance == amount)
            return balance + amount;
        if (balance > 0L)
            return balance;
        return amount;
    }
}
