package fr.lacombe.account;

class Account {

    static Account of(final long balance) {
        return new Account();
    }

    long deposit(final long amount) {
        return amount;
    }
}
