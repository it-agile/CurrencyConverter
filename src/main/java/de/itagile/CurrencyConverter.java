package de.itagile;

import static de.itagile.Money.money;

class CurrencyConverter {
    private ExchangeRates exchangeRates;

    CurrencyConverter(ConversionRateRetriever retriever) {
        this.exchangeRates = retriever.retrieve();
    }

    Money convert(Currency zielwaehrung, Money money) {
        return money(zielwaehrung,
                money.getAmount().multiply(
                        exchangeRates.getExchangeRate(money.getCurrency(), zielwaehrung)));
    }

}
