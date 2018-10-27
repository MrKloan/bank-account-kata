package fr.lacombe.account;

import org.junit.Test;

import static fr.lacombe.account.Operation.DEPOSIT;
import static fr.lacombe.account.Operation.WITHDRAWAL;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @Test
    public void should_add_the_deposit_amount_to_the_account_balance() {
        final Account account = Account.of(Amount.of(4L));
        final Amount depositAmount = Amount.of(2L);

        final Amount balance = account.deposit(depositAmount);

        assertThat(balance).isEqualTo(Amount.of(6L));
    }

    @Test
    public void should_subtract_the_withdrawal_amount_from_the_account_balance() {
        final Account account = Account.of(Amount.of(4L));
        final Amount withdrawalAmount = Amount.of(2L);

        final Amount balance = account.withdraw(withdrawalAmount);

        assertThat(balance).isEqualTo(Amount.of(2L));
    }

    @Test
    public void should_have_one_deposit_operation_in_the_account_history() {
        final Account account = Account.of(Amount.of(1L));

        account.deposit(Amount.of(1L));

        assertThat(account.seeHistory()).isEqualTo(singletonList(DEPOSIT));
    }

    @Test
    public void should_have_one_withdrawal_operation_in_the_account_history() {
        final Account account = Account.of(Amount.of(1L));

        account.withdraw(Amount.of(1L));

        assertThat(account.seeHistory()).isEqualTo(singletonList(WITHDRAWAL));
    }

    @Test
    public void should_have_a_detailed_operation_statement_in_the_account_history_when_i_make_a_deposit() {
        final Account account = Account.of(Amount.of(3L));

        account.deposit(Amount.of(5L));

        assertThat(account.seeDetailedHistory()).isEqualTo(History.of(singletonList(
                OperationStatement.of(DEPOSIT, Amount.of(5L), Amount.of(8L))
        )));
    }
}
