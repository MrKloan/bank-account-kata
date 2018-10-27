package fr.lacombe.account;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @Test
    public void should_make_a_deposit_of_0() {
        final Account account = Account.of(0L);

        final long result = account.deposit(0L);

        assertThat(result).isEqualTo(0L);
    }
}
