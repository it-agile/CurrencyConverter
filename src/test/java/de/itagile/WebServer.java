package de.itagile;

import io.javalin.Javalin;

public class WebServer {

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);
        app.get("/", ctx -> ctx.result("Hello World!!!!"));
        app.get("/:to/:money/:date", ctx -> {
            var to = ctx.pathParam("to");
            var money = Double.parseDouble(ctx.pathParam("money"));
            var date = ctx.pathParam("date");
            var converter = new CurrencyConverter();
            ctx.result("" + converter.convertFromEurTo(to, money, date));
        });
    }
}