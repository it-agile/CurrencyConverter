package de.itagile;

public final class Eur implements Currency {
    public static final Eur INSTANCE = new Eur();

    private Eur() {
    }

    @Override
    public CurrencyName getName() {
        return CurrencyName.EUR;
    }

    @Override
    public String toString() {
        return getName().name();
    }
}
