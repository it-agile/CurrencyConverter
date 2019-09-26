package de.itagile;

import org.junit.Test;

import static de.itagile.Currencies.DKK;
import static de.itagile.Currencies.EUR;
import static de.itagile.Decimal.create;
import static de.itagile.Money.money;
import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    @Test
    public void integrationTest() {
        CurrencyConverter converter = new CurrencyConverter(
                new HttpConversionRateRetriever("2019-04-17"));

        assertEquals(money(create(37.326), DKK),
                converter.convert(money(create(5), EUR), DKK));
    }

}
