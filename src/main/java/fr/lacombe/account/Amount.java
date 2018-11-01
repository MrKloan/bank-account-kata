package fr.lacombe.account;

import java.util.Objects;

class Amount {

    private final long cents;

    private Amount(final long cents) {
        this.cents = cents;
    }

    static Amount of(final long cents) {
        if (cents <= 0)
            throw new IllegalArgumentException("Amount must be positive");

        return new Amount(cents);
    }

    long addTo(final long amount) {
        return cents + amount;
    }

    long subtractFrom(final long amount) {
        return amount - cents;
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

    @Override
    public String toString() {
        return "Amount{" +
                "cents=" + cents +
                '}';
    }
}
