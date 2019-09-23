package de.itagile;

class CurrencyConverter {
    private ExchangeRateService exchangeRateService;

    CurrencyConverter(ConversionRateRetriever retriever) {
        this.exchangeRateService = retriever.retrieve();
    }

    <DestCurrency extends Currency>
    Money<DestCurrency> convert(Money<Eur> money, DestCurrency destCurrency) {
        return money.change(exchangeRateService.getExchangeRate(money.getCurrency(), destCurrency));
    }

}
