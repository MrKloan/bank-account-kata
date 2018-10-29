package fr.lacombe.account;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Supplier;

import static fr.lacombe.account.Operation.DEPOSIT;
import static fr.lacombe.account.Operation.WITHDRAWAL;

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
        accountStatement = accountStatement.update(DEPOSIT, timestampSupplier.get(), amount);
        return accountStatement.lastStatement();
    }

    Optional<OperationStatement> withdraw(final Amount amount) {
        accountStatement = accountStatement.update(WITHDRAWAL, timestampSupplier.get(), amount);
        return accountStatement.lastStatement();
    }

    AccountStatement getAccountStatement() {
        return accountStatement;
    }
}
