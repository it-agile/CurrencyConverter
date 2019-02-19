package de.itagile;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyCalcTest {

	public CurrencyCalc cc;
	private Currency oneEuro = new Currency("EUR", 1.0);
	
	@Before 
	public void setup() {
		cc = new CurrencyCalc(
				new CurrencyImporterStatic(),
				new CurrencyParserVeryBasicImpl());
	}

	@Test
	public void testConvertEuroToDollar() {
		assertThat(cc.convertEUR(oneEuro, "USD"), equalTo(new Currency("USD", 1.126)));
	}

	@Test
	public void testConvertEuroToReal() {
		assertThat(cc.convertEUR(oneEuro, "BRL"), equalTo(new Currency("BRL", 4.1852)));
	}

	@Test
	public void testConvertMoreEuroToReal() {
		Currency twoEuro = new Currency("EUR", 2.0);
		assertThat(cc.convertEUR(twoEuro, "BRL"), equalTo(new Currency("BRL", 8.3704)));
	}

	@Test
	public void testConvertIgnoreFromCurrency() {
		Currency oneUnknown = new Currency("Unknown", 1.0);
		assertThat(cc.convertEUR(oneUnknown, "USD"), equalTo(new Currency("USD", 1.126)));
	}

	@Test
	public void testCurrencies() {
		assertThat(cc.supportedCurrencyCodes(),hasItem("USD"));
		assertThat(cc.supportedCurrencyCodes(),hasItem("BRL"));
	}

	@Test(expected = NumberFormatException.class)
	public void testConvertUnknown() {
		cc.convertEUR(oneEuro, "An unknown Currency");
	}


}
