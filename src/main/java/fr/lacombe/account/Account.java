package fr.lacombe.account;

import java.util.Optional;

import static fr.lacombe.account.Operation.DEPOSIT;
import static fr.lacombe.account.Operation.WITHDRAWAL;

class Account {

    private History history;

    private Account(final History history) {
        this.history = history;
    }

    static Account empty() {
        return new Account(History.empty());
    }

    static Account of(final History history) {
        return new Account(history);
    }

    Optional<OperationStatement> deposit(final Amount amount) {
        history = history.put(DEPOSIT.execute(history.currentBalance(), amount));
        return history.lastStatement();
    }

    Optional<OperationStatement> withdraw(final Amount amount) {
        history = history.put(WITHDRAWAL.execute(history.currentBalance(), amount));
        return history.lastStatement();
    }

    History seeDetailedHistory() {
        return history;
    }
}
