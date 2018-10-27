package fr.lacombe.account;

class Account {

    private final long balance;

    private Account(final long balance) {
        this.balance = balance;
    }

    static Account of(final long balance) {
        return new Account(balance);
    }

    Amount deposit(final Amount amount) {
        return amount.add(balance);
    }
}
