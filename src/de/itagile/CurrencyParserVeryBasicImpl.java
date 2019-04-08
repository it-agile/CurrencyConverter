package de.itagile;

import java.util.HashMap;
import java.util.Map;

public class CurrencyParserVeryBasicImpl implements CurrencyParser {

    @Override
    public Map<String, Double> parseCurrencies(String jsonCurrencyString) {
        Map<String, Double> currencies = new HashMap<String, Double>();
        String[] currenciesStrings = jsonCurrencyString.substring(
                43, jsonCurrencyString.length() - 2).split(",");
        for (String crString : currenciesStrings) {
            currencies.put(crString.substring(1, 4), new Double(crString.split(":")[1]));
        }
        return currencies;
    }

}
