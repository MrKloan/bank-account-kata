package fr.lacombe.account;

import java.util.function.BiFunction;

enum OperationType {

    DEPOSIT(Balance::add),
    WITHDRAWAL(Balance::subtract);

    private BiFunction<Balance, Amount, Balance> operation;

    OperationType(final BiFunction<Balance, Amount, Balance> operation) {
        this.operation = operation;
    }

    Balance execute(final Balance balance, final Amount amount) {
        return operation.apply(balance, amount);
    }
}
