package fr.lacombe.account;

import java.time.LocalDateTime;
import java.util.List;
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

    static Account of(final Amount initialAmount, final Supplier<LocalDateTime> timestampSupplier) {
        final Account account = new Account(timestampSupplier, AccountStatement.empty());
        account.deposit(initialAmount);

        return account;
    }

    OperationStatement deposit(final Amount amount) {
        return handleOperation(DEPOSIT, amount);
    }

    OperationStatement withdraw(final Amount amount) {
        return handleOperation(WITHDRAWAL, amount);
    }

    private OperationStatement handleOperation(final OperationType operationType, final Amount amount) {
        final Operation operation = Operation.of(operationType, timestampSupplier.get(), amount);
        accountStatement = accountStatement.update(operation);

        return accountStatement.lastStatement();
    }

    List<OperationStatement> getOperations() {
        return accountStatement.getOperations();
    }
}
