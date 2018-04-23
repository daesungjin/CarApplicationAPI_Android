package com.example.user.carapplication.activity;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.carapplication.R;
import com.example.user.carapplication.thread.CarListReaderTask;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CarInventory extends ListActivity {
    public static final String INTENT_FILTER = "car_list";
    public static final String URL = "http://192.168.0.15:4567/";
    private ListView listView;
    private ArrayList<String> list;
    private ExecutorService es = null;
    private ArrayAdapter<String> adapter;
    private final CarInventory that = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_inventory);
        listView = findViewById(android.R.id.list);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,new IntentFilter(INTENT_FILTER));
        es = Executors.newSingleThreadExecutor();
        es.execute(new CarListReaderTask(getApplicationContext()));
    }
    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("OD", "Got broadcast message");
            if(intent.getAction().equals(INTENT_FILTER)){
                list = intent.getStringArrayListExtra("vin");
                adapter = new ArrayAdapter<String>(that.getBaseContext(),android.R.layout.activity_list_item, android.R.id.text1, list);
                listView.setAdapter(adapter);
            }
        }
    };

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(this, CarInfo.class);
        intent.putExtra("vin", list.get(position));
        //Log.d("intent", ""+list.get(position));
        startActivity(intent);
    }

    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        es.shutdownNow();
        super.onDestroy();
    }
}
