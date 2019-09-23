package de.itagile;

public final class Dkk implements Currency {
    public static final Dkk INSTANCE = new Dkk();

    private Dkk() {
    }

    @Override
    public CurrencyName getName() {
        return CurrencyName.DKK;
    }
}
