package fr.lacombe.account;

import java.util.function.BinaryOperator;

enum Operation {

    DEPOSIT(Amount::add),
    WITHDRAWAL(Amount::subtract);

    private BinaryOperator<Amount> operation;

    Operation(final BinaryOperator<Amount> operation) {
        this.operation = operation;
    }

    OperationStatement execute(final Amount balance, final Amount amount) {
        final Amount resultingBalance = operation.apply(balance, amount);
        return OperationStatement.of(this, amount, resultingBalance);
    }
}
