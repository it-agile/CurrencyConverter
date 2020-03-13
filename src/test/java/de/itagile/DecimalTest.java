package de.itagile;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DecimalTest {

    @Test
    public void multiplyWithPercent() {
       assertEquals(new Decimal(100.0).multiply(new Percent(5)),5);
    }
}
