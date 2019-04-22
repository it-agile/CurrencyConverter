package de.itagile;

import org.junit.Test;

import java.util.Map;

import static de.itagile.Money.Amount.amount;
import static de.itagile.Money.money;
import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    @Test
    public void integrationTest() {
        ConversionRateRetriever retriever = new HttpConversionRateRetriever();
        CurrencyConverter converter = new CurrencyConverter(retriever);

        assertEquals(money(Currency.DKK, amount(37.326)),
                converter.convert(Currency.DKK, money(Currency.EUR, amount(5))));
    }

    private class HttpConversionRateRetriever implements ConversionRateRetriever {

        private final CurrencyParser parser = new CurrencyParserVeryBasicImpl();
        private final CurrencyImporterWeb currencyImporter = new CurrencyImporterWeb();

        @Override
        public ExchangeRates retrieve() {
            return (ausgangswaehrung, zielwaehrung) -> {
                Map<String, Double> currencies = parser.parseCurrencies(currencyImporter.loadCurrencies("2019-04-17"));
                return amount(currencies.get(zielwaehrung.name()));
            };
        }
    }
}
