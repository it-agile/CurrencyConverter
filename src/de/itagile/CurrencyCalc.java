package de.itagile;
import java.util.Map;
import java.util.Set;

public class CurrencyCalc {
	
	CurrencyImporter importer;
	CurrencyParser parser;
	
	public CurrencyCalc(CurrencyImporter importer, CurrencyParser parser) {
		super();
		this.importer = importer;
		this.parser = parser;
	}

	/**
	 * Ignores Code from given Currency, assumes Euro
	 */
	public Currency convertEUR(Currency from , String toCode) {
		Map<String, Double> currencies = parser.parseCurrencies(importer.loadCurrencies());
	
		Double foreignCurrency = currencies.get(toCode);
		if(foreignCurrency == null) {
			throw new NumberFormatException("Unknown currency");
		}
		return new Currency(toCode, foreignCurrency * from.getValue());
	}
	
	public Set<String> supportedCurrencyCodes() {
		return parser.parseCurrencies(importer.loadCurrencies()).keySet();
	}


}
