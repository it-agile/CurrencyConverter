package de.itagile;

public class Money<MyCurrency extends Currency> {

    private final Decimal amount;
    private MyCurrency currency;

    private Money(MyCurrency zielwaehrung, Decimal amount) {
        this.currency = zielwaehrung;
        this.amount = amount;
    }

    static <C extends Currency> Money<C> money(Decimal amount, C myCurrency) {
        return new Money<>(myCurrency, amount);
    }

    MyCurrency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;

        Money<?> money = (Money<?>) o;

        if (!amount.equals(money.amount)) return false;
        return currency.equals(money.currency);
    }

    @Override
    public int hashCode() {
        int result = amount.hashCode();
        result = 31 * result + currency.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "de.itagile.Money{" +
                "currency=" + getCurrency() +
                ", amount=" + amount +
                '}';
    }

    public <DestCurrency extends Currency> Money<DestCurrency> change(ExchangeRate<MyCurrency, DestCurrency> exchangeRate) {
        return money(this.amount.multiply(exchangeRate.getRate()),
                exchangeRate.getDestCurrency());
    }

}
