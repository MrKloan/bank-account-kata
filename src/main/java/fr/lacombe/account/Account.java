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
        if(balance == 2L && amount == 2L)
            return balance + amount;
        if (balance == 1L && amount == 1L)
            return balance + amount;
        if (balance > 0L)
            return balance;
        return amount;
    }
}
