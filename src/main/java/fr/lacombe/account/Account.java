package fr.lacombe.account;

import static fr.lacombe.account.Operation.DEPOSIT;
import static fr.lacombe.account.Operation.WITHDRAWAL;

class Account {

    private final Amount balance;
    private History history;

    private Account(final Amount balance) {
        this.balance = balance;
        this.history = History.empty();
    }

    static Account of(final Amount balance) {
        return new Account(balance);
    }

    OperationStatement deposit(final Amount amount) {
        history = history.put(DEPOSIT.execute(balance, amount));
        return history.last();
    }

    OperationStatement withdraw(final Amount amount) {
        history = history.put(WITHDRAWAL.execute(balance, amount));
        return history.last();
    }

    History seeDetailedHistory() {
        return history;
    }
}
