package fr.lacombe.account;

class Account {

    private final Amount balance;

    private Account(final Amount balance) {
        this.balance = balance;
    }

    static Account of(final Amount balance) {
        return new Account(balance);
    }

    Amount deposit(final Amount amount) {
        return balance.add(amount);
    }

    Amount withdraw(final Amount amount) {
        return balance.subtract(amount);
    }
}
