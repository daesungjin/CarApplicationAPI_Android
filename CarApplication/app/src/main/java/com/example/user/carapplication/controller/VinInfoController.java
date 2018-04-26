package com.example.user.carapplication.controller;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by User on 2018-04-25.
 */

public class VinInfoController extends AppCompatTextView {
    public VinInfoController(Context context) {
        super(context);
    }
    public ArrayList<String> call_me(String vin) throws Exception{
        String url = "https://vpic.nhtsa.dot.gov/api/vehicles/decodevinvalues/"+vin+"?format=json";
        ArrayList<String> info = new ArrayList<>();
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int reponseCode = con.getResponseCode();
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
        for (Iterator<String> it = myResponse1.keys(); it.hasNext(); ) {
            String key = it.next();
            String k = (String)myResponse1.get(key);
            if(!k.isEmpty()) info.add(key+": "+k);
        }
        return info;
    }
}
