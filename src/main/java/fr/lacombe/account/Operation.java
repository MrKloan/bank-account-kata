package fr.lacombe.account;

import java.util.function.BiFunction;

enum Operation {

    DEPOSIT(Balance::add),
    WITHDRAWAL(Balance::subtract);

    private BiFunction<Balance, Amount, Balance> operation;

    Operation(final BiFunction<Balance, Amount, Balance> operation) {
        this.operation = operation;
    }

    OperationStatement execute(final Balance balance, final Amount amount) {
        final Balance resultingBalance = operation.apply(balance, amount);
        return OperationStatement.of(this, amount, resultingBalance);
    }
}
