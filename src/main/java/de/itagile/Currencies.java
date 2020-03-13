package de.itagile;

import java.util.Arrays;
import java.util.List;

public class Currencies {

    public static final Eur EUR = Eur.INSTANCE;
    public static final Dkk DKK = Dkk.INSTANCE;
    public static final Usd USD = Usd.INSTANCE;
    public static final List<Currency> ALL = Arrays.asList(EUR, DKK, USD);

    public static Currency create(CurrencyName currencyName) {
        for (Currency currency : ALL) {
            if (currency.getName().equals(currencyName)) {
                return currency;
            }
        }
        throw new IllegalStateException("Unexpected value: " + currencyName);
    }
}
