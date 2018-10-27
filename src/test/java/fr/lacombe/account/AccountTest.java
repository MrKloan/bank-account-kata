package fr.lacombe.account;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @Test
    public void should_add_the_deposit_amount_to_the_account_balance() {
        final Account account = Account.of(4L);

        final long balance = account.deposit(2L);

        assertThat(balance).isEqualTo(6L);
    }
}
