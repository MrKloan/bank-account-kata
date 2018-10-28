package fr.lacombe.account;

import java.util.Objects;

class Balance {

    private final long cents;

    private Balance(final long cents) {
        this.cents = cents;
    }

    static Balance empty() {
        return new Balance(0L);
    }

    static Balance of(final long cents) {
        return new Balance(cents);
    }

    Balance add(final Amount amount) {
        return new Balance(amount.addTo(cents));
    }

    Balance subtract(final Amount amount) {
        return new Balance(amount.subtractFrom(cents));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Balance balance = (Balance) o;
        return cents == balance.cents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cents);
    }
}
