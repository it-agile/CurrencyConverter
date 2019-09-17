package de.itagile;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;

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

    @Then("^The amount I receive via web is (\\d+\\.?\\d*)$")
    public void receiveViaWeb(double amount) {
        try {
            var content = "";
            URL url = new URL("http://localhost:7000/" + to +"/" + this.amount + "/" + date);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content += inputLine;
            }
            in.close();
            assertEquals(""+amount, content);
        } catch (Exception e) {
        }
    }
}