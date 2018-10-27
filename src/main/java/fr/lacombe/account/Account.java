package fr.lacombe.account;

import java.util.Collection;

import static fr.lacombe.account.Operation.DEPOSIT;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

class Account {

    private final Amount balance;
    private Operation operation;

    private Account(final Amount balance) {
        this.balance = balance;
    }

    static Account of(final Amount balance) {
        return new Account(balance);
    }

    Amount deposit(final Amount amount) {
        operation = DEPOSIT;
        return balance.add(amount);
    }

    Amount withdraw(final Amount amount) {
        return balance.subtract(amount);
    }

    Collection<Operation> seeHistory() {
        if(operation != null)
            return singletonList(operation);
        return emptyList();
    }
}
