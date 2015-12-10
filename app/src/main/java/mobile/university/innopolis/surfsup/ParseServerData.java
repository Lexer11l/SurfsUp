package mobile.university.innopolis.surfsup;

import com.google.gson.Gson;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Kirill on 10.12.2015.
 */
public class ParseServerData {

    private static final String API_KEY = "DACNh74aTCEWWtZcKhNMDjN7Ogtu4kvQxBBjLlvi";
    private static final String APP_ID = "bOlcY9aAR3Blk9uxQjd0Q6vgATKQ7Y2fwEmQrLbf";

    public ArrayList<Header> getHeaders() {
        ArrayList<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("X-Parse-REST-API-Key", API_KEY));
        headers.add(new BasicHeader("X-Parse-Application-Id", APP_ID));
        return headers;
    }

    public List<Bottle> getCoolerInfo() throws Exception {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "https://api.parse.com/1/classes/Values/";
        Bottles bottles = new Bottles();

        HttpGet httpGet = new HttpGet(url);
        for (Header header : getHeaders()) {
            httpGet.addHeader(header);
        }
        //httpGet.addHeader("X-Parse-Session-Token", auth); // taken from https://parse.com/questions/how-long-before-the-sessiontoken-expires

        try {
            HttpResponse response = httpClient.execute(httpGet);

            String responseString = EntityUtils.toString(response.getEntity());

            if (response.getStatusLine().getStatusCode() != HttpURLConnection.HTTP_OK) {
                /*ParseComServer.ParseComError error = new Gson()
                        .fromJson(responseString, ParseComServer.ParseComError.class);
                throw new Exception("Error retrieving ["+error.code+"] - " + error.error);*/
            }

            bottles = new Gson().fromJson(responseString, Bottles.class);
            //return locations.results;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bottles.results;
    }

    private class Bottles implements Serializable {
        List<Bottle> results;
    }

}
