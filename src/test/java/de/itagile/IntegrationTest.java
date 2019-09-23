package de.itagile;

import org.junit.Test;

import java.util.Map;

import static de.itagile.Currencies.DKK;
import static de.itagile.Currencies.EUR;
import static de.itagile.Decimal.create;
import static de.itagile.Money.money;
import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    @Test
    public void integrationTest() {
        ConversionRateRetriever retriever = new HttpConversionRateRetriever();
        CurrencyConverter converter = new CurrencyConverter(retriever);

        assertEquals(money(create(37.326), DKK),
                converter.convert(money(create(5), EUR), DKK));
    }

    private class HttpConversionRateRetriever implements ConversionRateRetriever {

        private final CurrencyParser parser = new CurrencyParserVeryBasicImpl();
        private final CurrencyImporterWeb currencyImporter = new CurrencyImporterWeb();

        @Override
        public ExchangeRateService retrieve() {
            return new ExchangeRateService() {
                @Override
                public <DestCurrency extends Currency> ExchangeRate<Eur, DestCurrency> getExchangeRate(Eur srcCurrency, DestCurrency destCurrency) {
                    Map<String, Double> currencies = parser.parseCurrencies(currencyImporter.loadCurrencies("2019-04-17"));
                    Double amount = currencies.get(destCurrency.getName().name());
                    return new EurExchangeRate<>(destCurrency, create(amount));
                }
            };
        }
    }
}
