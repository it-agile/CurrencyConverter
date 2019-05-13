package de.itagile;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import static org.junit.Assert.*;

public class Stepdefs {
    private String from;
    private String to;
    private double amount;
    private int fee;
    private final CurrencyConverter currencyConverter = new CurrencyConverter();
    private String date;

    @Given("^the date is (.+)$")
    public void date(String date) {
        this.date = date;
    }
    @Given("^the transaction fee is (\\d+)%$")
    public void transactionFee(int fee) {
        this.fee = fee;
    }

    @Given("^the amount is (\\d+) (\\w+)")
    public void amount(double amount, String from) {
        this.amount = amount;
        this.from = from;
    }

    @When("^I change it to (\\w+)$")
    public void amount(String to) {
        this.to = to;
    }

    @Then("^The amount I receive is (\\d+\\.?\\d*)$")
    public void receive(double amount) {
        assertEquals(
                amount,
                currencyConverter.convertWithFeeFromEurTo(this.to, this.amount, this.date, this.fee),
                0.01);
    }
}