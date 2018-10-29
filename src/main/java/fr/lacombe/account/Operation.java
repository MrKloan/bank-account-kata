package fr.lacombe.account;

import java.time.LocalDateTime;

class Operation {

    private final OperationType operationType;
    private final LocalDateTime timestamp;
    private final Amount amount;

    private Operation(final OperationType operationType, final LocalDateTime timestamp, final Amount amount) {
        this.operationType = operationType;
        this.timestamp = timestamp;
        this.amount = amount;
    }

    static Operation of(final OperationType operationType, final LocalDateTime timestamp, final Amount amount) {
        return new Operation(operationType, timestamp, amount);
    }

    OperationStatement computeStatement(final Balance balance) {
        final Balance nextBalance = operationType.execute(balance, amount);
        return OperationStatement.of(operationType, timestamp, amount, nextBalance);
    }
}
