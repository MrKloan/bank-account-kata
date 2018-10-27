package fr.lacombe.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.emptyList;

class History {

    private final List<OperationStatement> statements;

    private History(final List<OperationStatement> statements) {
        this.statements = statements;
    }

    static History empty() {
        return new History(emptyList());
    }

    static History of(final List<OperationStatement> statements) {
        return new History(statements);
    }

    History put(final OperationStatement statement) {
        final List<OperationStatement> statements = new ArrayList<>(this.statements);
        statements.add(statement);

        return new History(statements);
    }

    OperationStatement lastStatement() {
        return statements.get(statements.size() - 1);
    }

    Amount currentBalance() {
        if(statements.isEmpty())
            return Amount.of(0L);

        return lastStatement().getBalance();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final History history = (History) o;
        return Objects.equals(statements, history.statements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statements);
    }
}
