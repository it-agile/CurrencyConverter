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
        Map<String, Double> currencies = parser.parseCurrencies(new CurrencyImporterStatic().loadCurrencies());

        assertEquals(set("AUD", "BRL", "GBP", "CHF", "USD"), currencies.keySet());
        assertEquals(Double.valueOf(1.5836), currencies.get("AUD"));
        assertEquals(Double.valueOf(1.1), currencies.get("CHF"));
    }

}
