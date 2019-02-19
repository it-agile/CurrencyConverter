package de.itagile;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class CurrencyParserVeryBasicImplTest {

	@Test
	public void testParseElements() {
		CurrencyParser parser = new CurrencyParserVeryBasicImpl();
		Map<String, Double> currencies = parser.parseCurrencies(new CurrencyImporterStatic().loadCurrencies());
		
		assertThat(currencies.keySet(), hasItems("AUD", "BRL", "CHF", "USD"));
		assertThat(currencies.get("AUD"), equalTo(1.5836));
		assertThat(currencies.get("CHF"), equalTo(1.1));
	}

}
