package de.itagile;

public interface ExchangeRate<SrcCurrency extends Currency, DestCurrency extends Currency> {
    DestCurrency getDestCurrency();

    SrcCurrency getSrcCurrency();

    Decimal getRate();
}
