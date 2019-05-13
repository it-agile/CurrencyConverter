package de.itagile;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    @Test
    public void integrationTest() {
        CurrencyConverter currencyConverter = new CurrencyConverter();

        assertEquals(37.34,
                currencyConverter.convertFromEurTo("DKK", 5, "2019-01-01"),
                0.01);
    }

}
