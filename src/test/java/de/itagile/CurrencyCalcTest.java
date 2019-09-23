package de.itagile;

import org.junit.Test;

import static de.itagile.Currencies.DKK;
import static de.itagile.Currencies.EUR;
import static de.itagile.Decimal.create;
import static de.itagile.Money.money;
import static org.junit.Assert.assertEquals;

public class CurrencyCalcTest {

    @Test
    public void convertsCurrencies() {
        // given
        ConversionRateRetriever retriever = conversionRateFor1EurIs(7, DKK);

        // when
        CurrencyConverter currencyConverter = new CurrencyConverter(retriever);

        // then
        assertEquals(money(create(7), DKK),
                currencyConverter.convert(
                        money(create(1), EUR),
                        DKK));
    }

    private ConversionRateRetriever conversionRateFor1EurIs(final double amount, final Currency currency) {
        return () -> new ExchangeRateService() {
            @Override
            public <DestCurrency extends Currency> ExchangeRate<Eur, DestCurrency> getExchangeRate(Eur srcCurrency, DestCurrency destCurrency) {
                if (destCurrency.equals(currency)) {
                    return new EurExchangeRate<>(destCurrency, create(amount));
                }
                return new EurExchangeRate<>(destCurrency, create(1));
            }
        };
    }
}
