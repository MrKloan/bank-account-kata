package fr.lacombe.account;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BalanceTest {

    @Test
    public void should_add_an_amount_to_the_balance() {
        final Balance balance = Balance.of(2L);
        final Amount amount = Amount.of(3L);

        final Balance result = balance.add(amount);

        assertThat(result).isEqualTo(Balance.of(5L));
    }

    @Test
    public void should_subtract_an_amount_from_the_balance() {
        final Balance balance = Balance.of(5L);
        final Amount amount = Amount.of(3L);

        final Balance result = balance.subtract(amount);

        assertThat(result).isEqualTo(Balance.of(2L));
    }
}