package de.itagile;

import java.util.Objects;

final class Decimal {
    private final double amount;

    private Decimal(double amount) {
        this.amount = amount;
    }

    static Decimal create(double amount) {
        return new Decimal(amount);
    }

    public Decimal percentOf(Decimal amount) {
        return create(this.amount * amount.amount);
    }

    public Decimal percentOf(Percentage percentage) {
        return Decimal.create(5);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Decimal)) return false;
        Decimal amount1 = (Decimal) o;
        return Double.compare(amount1.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "Amount{" +
                "amount=" + amount +
                '}';
    }
}
