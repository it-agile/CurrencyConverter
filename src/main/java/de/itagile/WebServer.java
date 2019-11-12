package de.itagile;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import static de.itagile.Currencies.*;
import static de.itagile.Money.money;

public class WebServer {

    private static Javalin app;

    public static void main(String[] args) {
        start(7000);
    }

    public static void start(int port) {
        app = Javalin.create().start(port);
        app.get("/", ctx -> ctx.redirect("/USD/100/2019-01-01"));
        app.get("/:to/:money/:date", ctx -> {
            var to = CurrencyName.valueOf(ctx.pathParam("to"));
            var money = paramToMoney(ctx, "money");
            var date = ctx.pathParam("date");
            var converter = new CurrencyConverter(new HttpConversionRateRetriever(date));

            ctx.result("" + converter.convert(money, create(to)));
        });
    }

    @NotNull
    private static Money<Eur> paramToMoney(Context ctx, String param) {
        Decimal amount = Decimal.create(Double.parseDouble(ctx.pathParam(param)));
        return money(amount, EUR);
    }

    public void stop() {
        try {
            app.stop();
        } catch (Exception e) {

        }
    }
}