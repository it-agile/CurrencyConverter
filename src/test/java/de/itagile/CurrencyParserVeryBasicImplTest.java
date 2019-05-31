package de.itagile;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CurrencyParserVeryBasicImplTest {

    private static Set<String> set(String... strings) {
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(strings));
        return set;
    }

    @Test
    public void testParseElements() {
        CurrencyParser parser = new CurrencyParserVeryBasicImpl();
        Map<String, Double> currencies = parser.parseCurrencies(new CurrencyImporterStatic("{\"base\":\"EUR\",\"date\":\"2019-01-01\",\"rates\":"
                + "{\"AUD\":1.5836,"
                + "\"GBP\":0.87938,"
                + "\"USD\":1.126,"
                + "\"CHF\":1.1,"
                + "\"BRL\":4.1852}}").loadCurrencies());

        assertEquals(set("AUD", "BRL", "GBP", "CHF", "USD"), currencies.keySet());
        assertEquals(Double.valueOf(1.5836), currencies.get("AUD"));
        assertEquals(Double.valueOf(1.1), currencies.get("CHF"));
    }

    @Test
    public void testParseElementsFromExchangeRatesApi() {
        CurrencyParser parser = new CurrencyParserVeryBasicImpl();
        Map<String, Double> currencies = parser.parseCurrencies(
                new CurrencyImporterStatic(
                        "{\"base\":\"EUR\",\"rates\":{\"BGN\":1.9558,\"NZD\":1.6694,\"ILS\":4.0255,\"RUB\":73.268,\"CAD\":1.5042,\"USD\":1.1246,\"PHP\":58.633,\"CHF\":1.1245,\"ZAR\":15.8562,\"AUD\":1.5823,\"JPY\":125.36,\"TRY\":6.3781,\"HKD\":8.8247,\"MYR\":4.6123,\"THB\":35.942,\"HRK\":7.4318,\"NOK\":9.6305,\"IDR\":15927.71,\"DKK\":7.4651,\"CZK\":25.634,\"HUF\":321.54,\"GBP\":0.86183,\"MXN\":21.439,\"KRW\":1288.11,\"ISK\":133.6,\"SGD\":1.5243,\"BRL\":4.3558,\"PLN\":4.2887,\"INR\":78.3215,\"RON\":4.7512,\"CNY\":7.5561,\"SEK\":10.4325},\"date\":\"2019-04-08\"}").loadCurrencies());
        assertEquals(Double.valueOf(1.5823), currencies.get("AUD"));
        assertEquals(Double.valueOf(1.1245), currencies.get("CHF"));
    }

}
