package de.itagile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyImporterWeb implements CurrencyImporter {

    @Override
    public String loadCurrencies() {

        return loadCurrencies("latest");
    }

    public String loadCurrencies(String date) {
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

        return content;
    }

}
