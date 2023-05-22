package threadingapp;

import org.json.JSONObject;

import javax.xml.transform.Result;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ApiJsonData implements Serializable {
    private String currency;

    public ApiJsonData(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public ReqAndREsponse getResult() {
        String address = getApi("https://api.apilayer.com/currency_data/live?apikey=Sm5JgHcJuj6656vmPxcEOQp7Efk8Np5o");

        JSONObject jsonObject = new JSONObject(address);

        double result = jsonObject.getJSONObject("quotes").getDouble(currency);

        ReqAndREsponse result1 = new ReqAndREsponse(result);
        return result1;
    }
    public String getApi(String url) {
        StringBuffer buffer = new StringBuffer();

        try {
            URL url1 = new URL(url);
            URLConnection urlConnection = url1.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String data;

            while((data = bufferedReader.readLine()) != null) {
                buffer.append(data);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return  buffer.toString();
    }
}

