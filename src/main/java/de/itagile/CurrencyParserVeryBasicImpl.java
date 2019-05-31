package de.itagile;

import java.util.HashMap;
import java.util.Map;

public class CurrencyParserVeryBasicImpl implements CurrencyParser {

    @Override
    public Map<String, Double> parseCurrencies(String jsonCurrencyString) {
        Map<String, Double> currencies = new HashMap<>();
        String[] currenciesStrings = jsonCurrencyString.split("\":\"|\\{\"|\":|,\"|},\"|\"}|}}");
        for (int i = 0; i < currenciesStrings.length; i++) {
            String crString = currenciesStrings[i];
            if (crString.length() == 3 && !crString.contains(".")) {
                currencies.put(crString, Double.parseDouble(currenciesStrings[i + 1]));
            }
        }
        return currencies;
    }

}
