package de.itagile;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

import static de.itagile.Money.money;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Stepdefs {
    static AtomicInteger counter = new AtomicInteger(7000);
    private String from;
    private String to;
    private double amount;
    private int fee;
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
    public void receiveViaWeb(double amount) {
        WebServer webServer = new WebServer();
        try {
            int port = counter.addAndGet(1);
            WebServer.start(port);
            String content = "";
            URL url = new URL("http://localhost:" + port + "/" + to + "/" + this.amount + "/" + date);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content += inputLine;
            }
            in.close();
            assertEquals(toMoneyString(amount), content);
        } catch (Exception e) {
            try {
                webServer.stop();
            } catch (Exception ignore) {
            }
            fail(e.getMessage());
        }
    }

    @NotNull
    private String toMoneyString(double amount) {
        return money(Decimal.create(amount),
                Currencies.create(CurrencyName.valueOf(this.to))).toString();
    }
}