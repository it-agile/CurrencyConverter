package de.itagile;

public class EurExchangeRate<DestCurrency extends Currency> implements ExchangeRate<Eur, DestCurrency> {
    private final DestCurrency currency;
    private final Decimal rate;

    public EurExchangeRate(DestCurrency Currency, Decimal rate) {
        this.currency = Currency;
        this.rate = rate;
    }

    public static <DestCurrency extends Currency> EurExchangeRate<DestCurrency> createEurExchangeRate(DestCurrency Currency, Decimal rate) {
        return new EurExchangeRate<>(Currency, rate);
    }

    @Override
    public DestCurrency getDestCurrency() {
        return currency;
    }

    @Override
    public Eur getSrcCurrency() {
        return Eur.INSTANCE;
    }

    @Override
    public Decimal getRate() {
        return rate;
    }
}
