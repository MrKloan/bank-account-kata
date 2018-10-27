package fr.lacombe.account;

import java.util.Objects;

class Amount {

    private final long cents;

    private Amount(final long cents) {
        this.cents = cents;
    }

    static Amount of(final long cents) {
        return new Amount(cents);
    }

    Amount add(final long amount) {
        return Amount.of(cents + amount);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Amount amount = (Amount) o;
        return cents == amount.cents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cents);
    }
}
