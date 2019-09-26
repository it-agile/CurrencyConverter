package de.itagile;

public interface ExchangeRateService {
    <DestCurrency extends Currency> ExchangeRate<Eur, DestCurrency>
    getExchangeRate(Eur srcCurrency, DestCurrency destCurrency);
}
