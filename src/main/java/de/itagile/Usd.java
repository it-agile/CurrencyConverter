package de.itagile;

public final class Usd implements Currency {
    public static final Usd INSTANCE = new Usd();

    private Usd() {
    }

    @Override
    public CurrencyName getName() {
        return CurrencyName.USD;
    }

    @Override
    public String toString() {
        return getName().name();
    }
}
