package com.example.user.carapplication.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.carapplication.R;
import com.example.user.carapplication.thread.CarInfoReaderTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CarInfo extends AppCompatActivity {
    public static final String INTENT_FILTER1 = "car_info_upgrade";
    public static final String INTENT_KEY1 = "key";
    public static final String INTENT_KEY2 = "value";
    private ListView listView;
    private ExecutorService es = null;
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;
    private final CarInfo that = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);
        listView = findViewById(android.R.id.list);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,new IntentFilter(INTENT_FILTER1));
        Intent intent = getIntent();
        es = Executors.newSingleThreadExecutor();
        es.execute(new CarInfoReaderTask(getApplicationContext(), intent.getStringExtra("vin")));
    }
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(INTENT_FILTER1)){
                ArrayList<String> keys = intent.getStringArrayListExtra(INTENT_KEY1);
                ArrayList<String> values = intent.getStringArrayListExtra(INTENT_KEY2);
                final List<String> temp = new ArrayList<>();
                for(int i = 0 ; i < keys.size(); i++){
                    String info = keys.get(i)+": "+values.get(i);
                    temp.add(info);
                }

                adapter1 = new ArrayAdapter<String>(that.getBaseContext(),android.R.layout.activity_list_item, android.R.id.text1, temp);

                listView.setAdapter(adapter1);

            }
        }
    };


}
