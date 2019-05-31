package de.itagile;

public class CurrencyImporterStatic implements CurrencyImporter {

    private final String string;

    public CurrencyImporterStatic(String string) {
        this.string = string;
    }

    @Override
    public String loadCurrencies() {
        return string;
    }

}
