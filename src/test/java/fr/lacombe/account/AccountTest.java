package fr.lacombe.account;

import org.junit.Test;

import static fr.lacombe.account.Operation.DEPOSIT;
import static fr.lacombe.account.Operation.WITHDRAWAL;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @Test
    public void should_add_the_deposit_amount_to_the_account_balance() {
        final Account account = Account.of(Amount.of(4L));
        final Amount depositAmount = Amount.of(2L);

        final OperationStatement statement = account.deposit(depositAmount);

        assertThat(statement).isEqualTo(OperationStatement.of(
                DEPOSIT,
                Amount.of(2L),
                Amount.of(6L)
        ));
    }

    @Test
    public void should_subtract_the_withdrawal_amount_from_the_account_balance() {
        final Account account = Account.of(Amount.of(4L));
        final Amount withdrawalAmount = Amount.of(2L);

        final OperationStatement statement = account.withdraw(withdrawalAmount);

        assertThat(statement).isEqualTo(OperationStatement.of(
                WITHDRAWAL,
                Amount.of(2L),
                Amount.of(2L)
        ));
    }

    @Test
    public void should_have_a_detailed_operation_statement_in_the_account_history_when_i_make_a_deposit() {
        final Account account = Account.of(Amount.of(3L));

        account.deposit(Amount.of(5L));

        assertThat(account.seeDetailedHistory()).isEqualTo(History.of(asList(
                OperationStatement.of(DEPOSIT, Amount.of(3L), Amount.of(3L)),
                OperationStatement.of(DEPOSIT, Amount.of(5L), Amount.of(8L))
        )));
    }

    @Test
    public void should_have_a_detailed_operation_statement_in_the_account_history_when_i_make_a_withdrawal() {
        final Account account = Account.of(Amount.of(5L));

        account.withdraw(Amount.of(3L));

        assertThat(account.seeDetailedHistory()).isEqualTo(History.of(asList(
                OperationStatement.of(DEPOSIT, Amount.of(5L), Amount.of(5L)),
                OperationStatement.of(WITHDRAWAL, Amount.of(3L), Amount.of(2L))
        )));
    }

    @Test
    public void should_make_multiple_operations() {
        final Account account = Account.empty();

        account.deposit(Amount.of(3L));
        account.deposit(Amount.of(5L));
        account.deposit(Amount.of(7L));
        account.withdraw(Amount.of(5L));
        account.withdraw(Amount.of(3L));

        assertThat(account.seeDetailedHistory()).isEqualTo(History.of(asList(
                OperationStatement.of(DEPOSIT, Amount.of(3L), Amount.of(3L)),
                OperationStatement.of(DEPOSIT, Amount.of(5L), Amount.of(8L)),
                OperationStatement.of(DEPOSIT, Amount.of(7L), Amount.of(15L)),
                OperationStatement.of(WITHDRAWAL, Amount.of(5L), Amount.of(10L)),
                OperationStatement.of(WITHDRAWAL, Amount.of(3L), Amount.of(7L))
        )));
    }
}
