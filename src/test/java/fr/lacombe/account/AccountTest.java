package fr.lacombe.account;

import org.junit.Test;

import static fr.lacombe.account.Operation.DEPOSIT;
import static java.util.Collections.*;
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
    public void should_have_an_empty_history() {
        final Account account = Account.of(Amount.of(0L));

        assertThat(account.seeHistory()).isEqualTo(emptyList());
    }

    @Test
    public void should_have_1_operation_in_the_account_history() {
        final Account account = Account.of(Amount.of(0L));

        account.deposit(Amount.of(1L));

        assertThat(account.seeHistory()).isEqualTo(singletonList(DEPOSIT));
    }
}
