package com.example.user.carapplication.activity;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.carapplication.R;
import com.example.user.carapplication.thread.CarListReaderTask;
import com.example.user.carapplication.thread.VinInfoReaderTask;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VinInfo extends ListActivity {
    ExecutorService executorService = null;
    public static final String INTENT_FILTER = "Vin info";
    public static final String INTENT_KEY = "KEY INFO";
    private ListView listView=null;
    private VinInfo that = this;
    private ArrayList<String> list=null;
    private ArrayAdapter<String> arrayAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vin_info);
        listView = findViewById(android.R.id.list);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,new IntentFilter(INTENT_FILTER));
        Intent intent = getIntent();
        String vin = intent.getStringExtra("vin");
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new VinInfoReaderTask(this, vin));
    }
    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("OD", "Got broadcast message");
            if(intent.getAction().equals(INTENT_FILTER)){
                list = intent.getStringArrayListExtra(INTENT_KEY);
                //Log.d("listsize",list.size()+"");
                arrayAdapter = new ArrayAdapter<String>(that.getBaseContext(), android.R.layout.simple_list_item_1, list);

                //Log.d("item",adapter.getItem(0)+"");
                listView.setAdapter(arrayAdapter);
            }
        }
    };
    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        executorService.shutdownNow();
        super.onDestroy();
    }
}
