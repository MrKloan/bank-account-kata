package fr.lacombe.account;

import java.util.Optional;

import static fr.lacombe.account.Operation.DEPOSIT;
import static fr.lacombe.account.Operation.WITHDRAWAL;

class Account {

    private AccountStatement accountStatement;

    private Account(final AccountStatement accountStatement) {
        this.accountStatement = accountStatement;
    }

    static Account empty() {
        return new Account(AccountStatement.empty());
    }

    static Account of(final AccountStatement accountStatement) {
        return new Account(accountStatement);
    }

    Optional<OperationStatement> deposit(final Amount amount) {
        accountStatement = accountStatement.update(DEPOSIT, amount);
        return accountStatement.lastStatement();
    }

    Optional<OperationStatement> withdraw(final Amount amount) {
        accountStatement = accountStatement.update(WITHDRAWAL, amount);
        return accountStatement.lastStatement();
    }

    AccountStatement getAccountStatement() {
        return accountStatement;
    }
}
