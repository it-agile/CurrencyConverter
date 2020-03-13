package de.itagile;

import org.junit.Test;

import static de.itagile.Currencies.DKK;
import static de.itagile.Currencies.EUR;
import static de.itagile.Decimal.create;
import static de.itagile.EurExchangeRate.createEurExchangeRate;
import static de.itagile.Money.money;
import static org.junit.Assert.assertEquals;

public class MoneyTest {

    @Test
    public void changeEurToDkk() {
        Money<Eur> twoEur = money(create(2), EUR);
        assertEquals(money(create(14), DKK),
                twoEur.change(createEurExchangeRate(DKK, create(7))));
    }

    
}