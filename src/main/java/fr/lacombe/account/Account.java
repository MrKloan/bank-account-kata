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
        final OperationStatement depositStatement = DEPOSIT.execute(balance, amount);
        history = history.put(depositStatement);

        return depositStatement;
    }

    OperationStatement withdraw(final Amount amount) {
        final OperationStatement withdrawalStatement = WITHDRAWAL.execute(balance, amount);
        history = history.put(withdrawalStatement);

        return withdrawalStatement;
    }

    History seeDetailedHistory() {
        return history;
    }
}
