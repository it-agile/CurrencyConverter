package de.itagile;
import java.util.Map;

public interface CurrencyParser {

	Map<String, Double> parseCurrencies(String jsonCurrencyString);

}