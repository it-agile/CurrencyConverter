package de.itagile;

public class CurrencyConverter {

    private ExchangeRateService exchangeRateService;

    CurrencyConverter(ConversionRateRetriever retriever) {
        this.exchangeRateService = retriever.retrieve();
    }

    public <DestCurrency extends Currency> Money<DestCurrency> convert(Money<Eur> money, DestCurrency destCurrency) {
        return money.change(exchangeRateService.getExchangeRate(money.getCurrency(), destCurrency));
    }


    public <DestCurrency extends Currency> Money<DestCurrency> convert(Money<Eur> money, DestCurrency destCurrency, String date, Money<Eur> fee) {
        return convert(money, destCurrency);
    }
}
