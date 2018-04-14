package edu.oswego.torfquestions;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by Samsung on 2018-04-11.
 */

public class Question extends AppCompatTextView {
    public Question(Context context) {
        super(context);
    }
    public static void readFrom(String amount, String category, String difficulty, String type) throws IOException, JSONException {
        String url = "https://opentdb.com/api.php?amount="+amount+"&category="+category+"&difficulty="+difficulty+"&type="+type;
        HashMap<String,String> info = new HashMap<>();
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();

        JSONObject myResponse = new JSONObject(response.toString());
        String s = (String) myResponse.get("Results").toString();
        JSONObject myResponse1 = new JSONObject(s.substring(1,s.length()));
        for(String key : myResponse1.k){
            String k = (String)myResponse1.get(key);
            if(!k.isEmpty()) info.put(key, myResponse1.getString(key));
        }
        return info;
    }
}
