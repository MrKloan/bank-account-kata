package fr.lacombe.account;

import static fr.lacombe.account.Operation.DEPOSIT;
import static fr.lacombe.account.Operation.WITHDRAWAL;
import static java.util.Collections.singletonList;

class Account {

    private final Amount balance;
    private History history;

    private Account(final Amount balance, final History history) {
        this.balance = balance;
        this.history = history;
    }

    static Account empty() {
        return new Account(Amount.of(0L), History.empty());
    }

    static Account of(final History history) {
        return new Account(Amount.of(0L), history);
    }

    static Account of(final Amount balance) {
        return Account.of(History.of(singletonList(
                OperationStatement.of(DEPOSIT, balance, balance)
        )));
    }

    OperationStatement deposit(final Amount amount) {
        history = history.put(DEPOSIT.execute(history.currentBalance(), amount));
        return history.lastStatement();
    }

    OperationStatement withdraw(final Amount amount) {
        history = history.put(WITHDRAWAL.execute(history.currentBalance(), amount));
        return history.lastStatement();
    }

    History seeDetailedHistory() {
        return history;
    }
}
