package de.itagile;

public class Currencies {

    public static final Eur EUR = Eur.INSTANCE;
    public static final Dkk DKK = Dkk.INSTANCE;
    public static final Usd USD = Usd.INSTANCE;

    public static Currency create(CurrencyName currencyName) {
        switch (currencyName) {
            case EUR:
                return EUR;
            case DKK:
                return DKK;
            case USD:
                return USD;
            default:
                throw new IllegalStateException("Unexpected value: " + currencyName);
        }
    }
}
