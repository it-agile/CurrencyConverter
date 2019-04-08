package de.itagile;

public interface ExchangeRates {
    Money.Amount getExchangeRate(Currency ausgangswaehrung, Currency zielwaehrung);
}
