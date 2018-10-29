package fr.lacombe.account;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static fr.lacombe.account.Operation.DEPOSIT;
import static fr.lacombe.account.Operation.WITHDRAWAL;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @Test
    public void should_add_the_deposit_amount_to_the_account_balance() {
        final LocalDateTime timestamp = LocalDateTime.now();
        final Account account = Account.of(
                () -> timestamp,
                AccountStatement.of(singletonList(
                        OperationStatement.of(DEPOSIT, timestamp, Amount.of(4L), Balance.of(4L))
                ))
        );

        final Optional<OperationStatement> statement = account.deposit(Amount.of(2L));

        assertThat(statement).isEqualTo(Optional.of(
                OperationStatement.of(DEPOSIT, timestamp, Amount.of(2L), Balance.of(6L))
        ));
    }

    @Test
    public void should_subtract_the_withdrawal_amount_from_the_account_balance() {
        final LocalDateTime timestamp = LocalDateTime.now();
        final Account account = Account.of(
                () -> timestamp,
                AccountStatement.of(singletonList(
                        OperationStatement.of(DEPOSIT, timestamp, Amount.of(4L), Balance.of(4L))
                ))
        );

        final Optional<OperationStatement> statement = account.withdraw(Amount.of(2L));

        assertThat(statement).isEqualTo(Optional.of(
                OperationStatement.of(WITHDRAWAL, timestamp, Amount.of(2L), Balance.of(2L))
        ));
    }

    @Test
    public void should_subtract_the_withdrawal_amount_from_the_account_balance_and_have_a_negative_balance() {
        final LocalDateTime timestamp = LocalDateTime.now();
        final Account account = Account.of(
                () -> timestamp,
                AccountStatement.of(singletonList(
                        OperationStatement.of(DEPOSIT, timestamp, Amount.of(5L), Balance.of(5L))
                ))
        );

        final Optional<OperationStatement> statement = account.withdraw(Amount.of(7L));

        assertThat(statement).isEqualTo(Optional.of(
                OperationStatement.of(WITHDRAWAL, timestamp, Amount.of(7L), Balance.of(-2L))
        ));
    }

    @Test
    public void should_get_the_complete_account_statement() {
        final LocalDateTime timestamp = LocalDateTime.now();
        final Account account = Account.of(
                () -> timestamp,
                AccountStatement.empty()
        );

        account.deposit(Amount.of(3L));
        account.deposit(Amount.of(5L));
        account.deposit(Amount.of(7L));
        account.withdraw(Amount.of(5L));
        account.withdraw(Amount.of(3L));

        assertThat(account.getAccountStatement()).isEqualTo(AccountStatement.of(asList(
                OperationStatement.of(DEPOSIT, timestamp, Amount.of(3L), Balance.of(3L)),
                OperationStatement.of(DEPOSIT, timestamp, Amount.of(5L), Balance.of(8L)),
                OperationStatement.of(DEPOSIT, timestamp, Amount.of(7L), Balance.of(15L)),
                OperationStatement.of(WITHDRAWAL, timestamp, Amount.of(5L), Balance.of(10L)),
                OperationStatement.of(WITHDRAWAL, timestamp, Amount.of(3L), Balance.of(7L))
        )));
    }
}
