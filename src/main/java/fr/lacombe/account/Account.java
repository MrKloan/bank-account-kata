package fr.lacombe.account;

import java.util.ArrayList;
import java.util.Collection;

import static fr.lacombe.account.Operation.DEPOSIT;
import static fr.lacombe.account.Operation.WITHDRAWAL;

class Account {

    private final Amount balance;
    private Collection<Operation> operations;

    private Account(final Amount balance) {
        this.balance = balance;
        this.operations = new ArrayList<>();
    }

    static Account of(final Amount balance) {
        return new Account(balance);
    }

    Amount deposit(final Amount amount) {
        operations.add(DEPOSIT);
        return DEPOSIT.execute(balance, amount);
    }

    Amount withdraw(final Amount amount) {
        operations.add(WITHDRAWAL);
        return WITHDRAWAL.execute(balance, amount);
    }

    Collection<Operation> seeHistory() {
        return operations;
    }
}
