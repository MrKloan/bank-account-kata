package fr.lacombe.account;

import java.time.LocalDateTime;
import java.util.Objects;

class OperationStatement {

    private final OperationType operationType;
    private final LocalDateTime timestamp;
    private final Amount amount;
    private final Balance balance;

    private OperationStatement(final OperationType operationType, final LocalDateTime timestamp, final Amount amount, final Balance balance) {
        this.operationType = operationType;
        this.timestamp = timestamp;
        this.amount = amount;
        this.balance = balance;
    }

    static OperationStatement of(final OperationType operationType, final LocalDateTime timestamp, final Amount amount, final Balance balance) {
        return new OperationStatement(operationType, timestamp, amount, balance);
    }

    OperationStatement calculateNext(final Operation operation) {
        return operation.computeStatement(balance);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OperationStatement that = (OperationStatement) o;
        return operationType == that.operationType &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationType, timestamp, amount, balance);
    }

    @Override
    public String toString() {
        return "OperationStatement{" +
                "operationType=" + operationType +
                ", timestamp=" + timestamp +
                ", amount=" + amount +
                ", balance=" + balance +
                '}';
    }
}
