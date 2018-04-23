package com.example.user.carapplication.controller;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import com.example.user.carapplication.activity.CarInventory;
import com.example.user.carapplication.activity.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by User on 2018-04-22.
 */

public class CarController extends AppCompatTextView {
    public CarController(Context context) {
        super(context);
    }

    public CarController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CarController(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public List<String> getCarVinList() throws IOException, JSONException {
        ArrayList<String> list = new ArrayList<>();
        String url = CarInventory.URL+"car";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        InputStreamReader is =new InputStreamReader(con.getInputStream());
        BufferedReader in = new BufferedReader(is);
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //Log.d("response", response.toString());
        JSONArray jsonArray = new JSONArray(response.toString());
        for(int i = 0 ; i < jsonArray.length(); i++){
             JSONObject jsonObject = (JSONObject) jsonArray.get(i);
             Log.d("OD",""+jsonArray.get(i).toString());
             String temp = jsonObject.getString("vin");

             list.add(temp);
        }
        return list;
    }
    public List<String> getCarNameList() throws IOException, JSONException {
        ArrayList<String> list = new ArrayList<>();
        String url = CarInventory.URL+"car";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        InputStreamReader is =new InputStreamReader(con.getInputStream());
        BufferedReader in = new BufferedReader(is);
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONArray jsonArray = new JSONArray(response.toString());
        for(int i = 0 ; i < jsonArray.length(); i++){
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            String temp = jsonObject.getString("carName");
            list.add(temp);
        }
        return list;
    }
    public List<String> getCarInfoValues(String vin) throws IOException, JSONException {
        List<String> list = new ArrayList<>();
        String url = CarInventory.URL+"car/"+vin;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        InputStreamReader is =new InputStreamReader(con.getInputStream());
        BufferedReader in = new BufferedReader(is);
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject jsonObject = new JSONArray(response.toString()).getJSONObject(0);
        list.add(jsonObject.getString("carName"));
        list.add(jsonObject.getString("vin"));
        JSONArray jsonArray = jsonObject.getJSONArray("carInfo");
        for(int i = 0 ; i < jsonArray.length() ; i++){
            JSONObject temp = (JSONObject) jsonArray.get(i);
            for (Iterator<String> it = temp.keys(); it.hasNext(); ) {
                String key = it.next();
                list.add(temp.getString(key));

            }
        }
        return list;
    }
    public List<String> getCarInfoKeys(String vin) throws IOException, JSONException {
        List<String> list = new ArrayList<>();
        String url = CarInventory.URL+"car/"+vin;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        InputStreamReader is =new InputStreamReader(con.getInputStream());
        BufferedReader in = new BufferedReader(is);
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        Log.d("response", ""+response.toString());
        JSONObject jsonObject = new JSONArray(response.toString()).getJSONObject(0);
        list.add("carName");
        list.add("vin");
        JSONArray jsonArray = jsonObject.getJSONArray("carInfo");
        for(int i = 0 ; i < jsonArray.length() ; i++){
            JSONObject temp = (JSONObject) jsonArray.get(i);
            for (Iterator<String> it = temp.keys(); it.hasNext(); ) {
                String key = it.next();
                list.add(key);
            }
        }
        return list;
    }

}
