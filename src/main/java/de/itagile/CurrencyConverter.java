package de.itagile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {

    public double convertFromEurTo(String to, double amount, String date) {
        String content = "";
        try {
            URL url = new URL("https://api.exchangeratesapi.io/" + date);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content += inputLine;
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            content = "ERROR";
        }
        Map<String, Double> currencies = new HashMap<>();
        String[] currenciesStrings = content.split("\":\"|\\{\"|\":|,\"|},\"|\"}|}}");
        for (int i = 0; i < currenciesStrings.length; i++) {
            String crString = currenciesStrings[i];
            if (crString.length() == 3 && !crString.contains(".")) {
                currencies.put(crString, Double.parseDouble(currenciesStrings[i + 1]));
            }
        }
        return currencies.get(to) * amount;
    }

    public double convertWithFeeFromEurTo(String to, double amount, String date, int fee) {
        return convertFromEurTo(to, amount, date);
    }

}
