package de.itagile;

import java.util.Map;

import static de.itagile.Decimal.create;

public class HttpConversionRateRetriever implements ConversionRateRetriever {

    private final CurrencyParser parser = new CurrencyParserVeryBasicImpl();
    private final CurrencyImporterWeb currencyImporter = new CurrencyImporterWeb();
    private final String date;

    public HttpConversionRateRetriever(String date) {
        this.date = date;
    }

    @Override
    public ExchangeRateService retrieve() {
        return new ExchangeRateService() {
            @Override
            public <DestCurrency extends Currency> ExchangeRate<Eur, DestCurrency> getExchangeRate(Eur srcCurrency, DestCurrency destCurrency) {
                String jsonCurrencyString = currencyImporter.loadCurrencies(date);
                Map<String, Double> currencies = parser.parseCurrencies(jsonCurrencyString);
                Double amount = currencies.get(destCurrency.getName().name());
                return new EurExchangeRate<>(destCurrency, create(amount));
            }
        };
    }
}
