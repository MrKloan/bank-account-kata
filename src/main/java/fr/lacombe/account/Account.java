package fr.lacombe.account;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Supplier;

import static fr.lacombe.account.OperationType.DEPOSIT;
import static fr.lacombe.account.OperationType.WITHDRAWAL;

class Account {

    private final Supplier<LocalDateTime> timestampSupplier;
    private AccountStatement accountStatement;

    private Account(final Supplier<LocalDateTime> timestampSupplier, final AccountStatement accountStatement) {
        this.timestampSupplier = timestampSupplier;
        this.accountStatement = accountStatement;
    }

    static Account empty() {
        return new Account(LocalDateTime::now, AccountStatement.empty());
    }

    static Account of(final Supplier<LocalDateTime> timestampSupplier, final AccountStatement accountStatement) {
        return new Account(timestampSupplier, accountStatement);
    }

    Optional<OperationStatement> deposit(final Amount amount) {
         return handleOperation(DEPOSIT, amount);
    }

    Optional<OperationStatement> withdraw(final Amount amount) {
        return handleOperation(WITHDRAWAL, amount);
    }

    private Optional<OperationStatement> handleOperation(final OperationType operationType, final Amount amount) {
        final Operation operation = Operation.of(operationType, timestampSupplier.get(), amount);
        accountStatement = accountStatement.update(operation);

        return accountStatement.lastStatement();
    }

    AccountStatement getAccountStatement() {
        return accountStatement;
    }
}
