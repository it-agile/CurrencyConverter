package de.itagile;

public class CurrencyImporterStatic implements CurrencyImporter {

    @Override
    public String loadCurrencies() {
        return "{\"base\":\"EUR\",\"date\":\"2019-01-01\",\"rates\":"
                + "{\"AUD\":1.5836,"
                + "\"GBP\":0.87938,"
                + "\"USD\":1.126,"
                + "\"CHF\":1.1,"
                + "\"BRL\":4.1852}}";
    }

}
