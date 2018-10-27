package fr.lacombe.account;

import java.util.ArrayList;
import java.util.Collection;

import static fr.lacombe.account.Operation.DEPOSIT;
import static fr.lacombe.account.Operation.WITHDRAWAL;

class Account {

    private final Amount balance;
    private Collection<Operation> operations;

    private History history;

    private Account(final Amount balance) {
        this.balance = balance;
        this.operations = new ArrayList<>();
        this.history = History.empty();
    }

    static Account of(final Amount balance) {
        return new Account(balance);
    }

    Amount deposit(final Amount amount) {
        final Amount resultingBalance = DEPOSIT.execute(balance, amount);
        final OperationStatement statement = OperationStatement.of(DEPOSIT, amount, resultingBalance);
        history = history.put(statement);

        operations.add(DEPOSIT);
        return resultingBalance;
    }

    Amount withdraw(final Amount amount) {
        final Amount resultingBalance = WITHDRAWAL.execute(balance, amount);
        final OperationStatement statement = OperationStatement.of(WITHDRAWAL, amount, resultingBalance);
        history = history.put(statement);

        operations.add(WITHDRAWAL);
        return resultingBalance;
    }

    Collection<Operation> seeHistory() {
        return operations;
    }

    History seeDetailedHistory() {
       return history;
    }
}
