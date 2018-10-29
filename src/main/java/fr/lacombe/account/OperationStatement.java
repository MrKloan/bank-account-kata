package fr.lacombe.account;

import java.time.LocalDateTime;
import java.util.Objects;

class OperationStatement {

    private final Operation operation;
    private final LocalDateTime timestamp;
    private final Amount amount;
    private final Balance balance;

    private OperationStatement(final Operation operation, final LocalDateTime timestamp, final Amount amount, final Balance balance) {
        this.operation = operation;
        this.timestamp = timestamp;
        this.amount = amount;
        this.balance = balance;
    }

    static OperationStatement of(final Operation operation, final LocalDateTime timestamp, final Amount amount) {
        return new OperationStatement(operation, timestamp, amount, operation.execute(Balance.empty(), amount));
    }

    static OperationStatement of(final Operation operation, final LocalDateTime timestamp, final Amount amount, final Balance balance) {
        return new OperationStatement(operation, timestamp, amount, balance);
    }

    OperationStatement calculateNext(final Operation operation, final LocalDateTime timestamp, final Amount amount) {
        final Balance nextBalance = operation.execute(balance, amount);
        return new OperationStatement(operation, timestamp, amount, nextBalance);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OperationStatement that = (OperationStatement) o;
        return operation == that.operation &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation, timestamp, amount, balance);
    }
}
