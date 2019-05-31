package de.itagile;

import java.util.Objects;

public class Money {

    private final Amount amount;
    private Currency currency;

    private Money(Currency zielwaehrung, Amount amount) {
        this.currency = zielwaehrung;
        this.amount = amount;
    }

    static Money money(Currency zielwaehrung, Amount amount) {
        return new Money(zielwaehrung, amount);
    }

    Currency getCurrency() {
        return currency;
    }

    Amount getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        Money money = (Money) o;
        return currency == money.currency &&
                amount.equals(money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }

    @Override
    public String toString() {
        return "de.itagile.Money{" +
                "currency=" + getCurrency() +
                ", amount=" + amount +
                '}';
    }

    final static class Amount {
        private final double amount;

        private Amount(double amount) {
            this.amount = amount;
        }

        static Amount amount(double amount) {
            return new Amount(amount);
        }

        Amount multiply(Amount amount) {
            return amount(this.amount * amount.amount);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Amount)) return false;
            Amount amount1 = (Amount) o;
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
}
