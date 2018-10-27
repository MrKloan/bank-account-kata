package fr.lacombe.account;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AmountTest {

    @Test
    public void should_add_a_long_value_to_an_amount() {
        final Amount firstAmount = Amount.of(4L);

        final Amount result = firstAmount.add(2L);

        assertThat(result).isEqualTo(Amount.of(6L));
    }

}