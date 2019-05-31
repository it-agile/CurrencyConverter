package de.itagile;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static de.itagile.Money.Amount.amount;
import static org.junit.Assert.assertEquals;

public class CurrencyCalcTest {

    private static Money money(Currency eur, double amount) {
        return Money.money(eur, amount(amount));
    }

    private static void addExchangeRateFromTo(Map<CurrencyRelation, Money.Amount> exchangeRate, Currency from, Currency to, double amount) {
        exchangeRate.put(fromCurrencyToCurrency(from, to), amount(amount));
    }

    private static CurrencyRelation fromCurrencyToCurrency(Currency from, Currency to) {
        return new CurrencyRelation(from, to);
    }

    @Test
    public void convertsCurrencies() {
        Map<CurrencyRelation, Money.Amount> exchangeRates = new HashMap<>();
        addExchangeRateFromTo(exchangeRates, Currency.EUR, Currency.EUR, 1.0);
        addExchangeRateFromTo(exchangeRates, Currency.EUR, Currency.DKK, 7.0);
        addExchangeRateFromTo(exchangeRates, Currency.DKK, Currency.EUR, 1.0 / 7.0);
        addExchangeRateFromTo(exchangeRates, Currency.DKK, Currency.DKK, 1.0);

        ConversionRateRetriever retriever =
                () -> (ExchangeRates) (ausgangswaehrung, zielwaehrung)
                        -> exchangeRates.get(fromCurrencyToCurrency(ausgangswaehrung, zielwaehrung));

        CurrencyConverter currencyConverter = new CurrencyConverter(retriever);

        assertEquals(money(Currency.EUR, 1.0 / 7.0),
                currencyConverter.convert(Currency.EUR, money(Currency.DKK, 1)));
        assertEquals(money(Currency.EUR, 2.0 / 7.0),
                currencyConverter.convert(Currency.EUR, money(Currency.DKK, 2)));
        assertEquals(money(Currency.EUR, 1),
                currencyConverter.convert(Currency.EUR, money(Currency.EUR, 1)));
        assertEquals(money(Currency.DKK, 1),
                currencyConverter.convert(Currency.DKK, money(Currency.DKK, 1)));
    }

    private static class CurrencyRelation {

        private final Currency from;
        private final Currency to;

        private CurrencyRelation(Currency from, Currency to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CurrencyRelation)) return false;
            CurrencyRelation that = (CurrencyRelation) o;
            return from == that.from &&
                    to == that.to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }

    }

}
