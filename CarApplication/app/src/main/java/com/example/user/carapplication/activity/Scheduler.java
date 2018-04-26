package com.example.user.carapplication.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.carapplication.R;
import com.example.user.carapplication.db.ScheduleDatabase;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scheduler extends ListActivity {
    private TextView textView=null;
    static ScheduleDatabase scheduleDatabase = null;
    ArrayAdapter<String> arrayAdapter = null;
    ListView listView =null;
    private  int num = 0;
    long lastPressTime = 0;
    private static final long DOUBLE_PRESS_INTERVAL = 250;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
        scheduleDatabase = new ScheduleDatabase(this);


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        long pressTime = System.currentTimeMillis();
        String temp = (String)l.getItemAtPosition(position);
        String temp2 = temp.substring(3,6);
        String pattern = "\\\\d+";
        String temp4 = "";
        for(char c : temp2.toCharArray()){
            String temp3 = c+"";
            if(temp3.matches("[0-9]")){
                temp4 += temp3;
            }
        }

        if(isDoubleClick(pressTime)){
            scheduleDatabase.deleteData(temp4);
            showMessage("Delete","Delete successfully");
        }

    }
    private boolean isDoubleClick(long pressTime){
        if (pressTime - lastPressTime <= DOUBLE_PRESS_INTERVAL) {
            lastPressTime = pressTime;
            return true;
        }
        lastPressTime = pressTime;
        return false;
    }
    public void show(View v){
        Cursor res = scheduleDatabase.getAllData();
        ArrayList<String> temp = new ArrayList<String>();
        if(res!=null){
        while (res.moveToNext()) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("Id :"+ res.getString(0)+" ");
            buffer.append("customer Name :"+ res.getString(1)+" ");
            buffer.append("Start :"+ res.getString(2)+" ");
            buffer.append("End :"+ res.getString(3)+" ");
            temp.add(buffer.toString());
        }}
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,temp);
        listView = findViewById(android.R.id.list);
        listView.setAdapter(arrayAdapter);
    }
    public void create(View v){
        Intent intent = new Intent(this,CreateSchedule.class);
        startActivity(intent);
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
