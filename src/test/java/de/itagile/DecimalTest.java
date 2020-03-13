package de.itagile;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DecimalTest {

    @Test
    public void multiplyWithPercent() {
       assertEquals(Decimal.create(5),
               Decimal.create(100.0).percentOf(new Percentage(Decimal.create(5))));
    }
}
