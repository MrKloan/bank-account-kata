package fr.lacombe.account;

import java.util.Objects;

class OperationStatement {

    private final Operation operation;
    private final Amount amount;
    private final Amount balance;

    private OperationStatement(final Operation operation, final Amount amount, final Amount balance) {
        this.operation = operation;
        this.amount = amount;
        this.balance = balance;
    }

    static OperationStatement of(final Operation operation, final Amount amount, final Amount balance) {
        return new OperationStatement(operation, amount, balance);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OperationStatement that = (OperationStatement) o;
        return operation == that.operation &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation, amount, balance);
    }
}
