package fr.lacombe.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Collections.emptyList;

class AccountStatement {

    private final List<OperationStatement> operationStatements;

    private AccountStatement(final List<OperationStatement> operationStatements) {
        this.operationStatements = operationStatements;
    }

    static AccountStatement empty() {
        return new AccountStatement(emptyList());
    }

    static AccountStatement of(final List<OperationStatement> operationStatements) {
        return new AccountStatement(operationStatements);
    }

    AccountStatement update(final Operation operation, final Amount amount) {
        final OperationStatement nextStatement = lastStatement()
                .map(statement -> statement.calculateNext(operation, amount))
                .orElseGet(() -> OperationStatement.of(operation, amount));

        return writeStatement(nextStatement);
    }

    Optional<OperationStatement> lastStatement() {
        if (operationStatements.isEmpty())
            return Optional.empty();

        return Optional.of(operationStatements.get(operationStatements.size() - 1));
    }

    private AccountStatement writeStatement(final OperationStatement statement) {
        final List<OperationStatement> statements = new ArrayList<>(this.operationStatements);
        statements.add(statement);

        return new AccountStatement(statements);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AccountStatement accountStatement = (AccountStatement) o;
        return Objects.equals(operationStatements, accountStatement.operationStatements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationStatements);
    }
}