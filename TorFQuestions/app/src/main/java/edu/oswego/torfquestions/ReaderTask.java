package edu.oswego.torfquestions;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by User on 2018-04-14.
 */

public class ReaderTask implements Runnable {
    private Context context;
    private String amount, category, difficulty, type;
    public ReaderTask(Context context, String a, String c, String d, String t){
        this.context = context;
        this.amount = a;
        this.category = c;
        this.difficulty = d;
        this.type = t;
    }
    @Override
    public void run() {
        //https://opentdb.com/api.php?amount=10&category=9&difficulty=medium&type=multiple
        String s = "";
        String info = "";
        try {
            info = new Question(context).readFrom(amount,category,difficulty,type);

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("od", "IOException");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("od", "JSONException");
        }

//        if(info.isEmpty())
//        Log.d("od","s is null");
//        else Log.d("od", ""+s.length());
        Intent intent = new Intent("info");
        intent.putExtra("info",info);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
