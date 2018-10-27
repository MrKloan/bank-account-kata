package fr.lacombe.account;

import java.util.function.BinaryOperator;

enum Operation {

    DEPOSIT(Amount::add),
    WITHDRAWAL(Amount::subtract);

    private BinaryOperator<Amount> operation;

    Operation(final BinaryOperator<Amount> operation) {
        this.operation = operation;
    }

    Amount execute(final Amount balance, final Amount amount) {
        return operation.apply(balance, amount);
    }
}
