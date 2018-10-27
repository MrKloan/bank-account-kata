package fr.lacombe.account;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AmountTest {

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_when_creating_an_amount_of_zero() {
        Amount.of(0L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_when_creating_a_negative_amount() {
        Amount.of(-1L);
    }

    @Test
    public void should_add_a_long_to_an_amount() {
        final Amount firstAmount = Amount.of(4L);

        final long result = firstAmount.addTo(2L);

        assertThat(result).isEqualTo(6L);
    }

    @Test
    public void should_subtract_a_long_from_an_amount() {
        final Amount firstAmount = Amount.of(2L);

        final long result = firstAmount.subtractFrom(4L);

        assertThat(result).isEqualTo(2L);
    }
}