package com.example.user.carapplication.activity;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.carapplication.R;
import com.example.user.carapplication.thread.CarInfoReaderTask;
import com.example.user.carapplication.thread.DeleteCarTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CarInfo extends ListActivity {
    public static final String INTENT_FILTER1 = "car_info_upgrade";
    public static final String INTENT_KEY1 = "key";
    public static final String INTENT_KEY2 = "value";
    private ListView listView;
    private ExecutorService es = null;
    private ExecutorService executorService = null;
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;
    private final CarInfo that = this;

    ArrayList<String> keys = null;
    ArrayList<String> values =null;
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

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String key =(String) l.getItemAtPosition(position);
        if(key.contains("vin")){
            String temp = key.substring(4);
            Intent intent = new Intent(this,VinInfo.class);
            intent.putExtra("vin",temp);
            startActivity(intent);
            Log.d("temp",""+temp);
        }
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        es.shutdownNow();
        super.onDestroy();
    }
    public void insert(View v){
        Intent intent = new Intent(this,InsertInfo.class);
        intent.putExtra("vin",getIntent().getStringExtra("vin"));
        startActivity(intent);
        finish();
    }
    public void delete(View v){
        Intent intent = getIntent();
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new DeleteCarTask(getApplicationContext(), intent.getStringExtra("vin"),values));
        Intent intent1 = new Intent(this,CarInventory.class);
        startActivity(intent1);
        showMessage("delete", "delete successfully!");
        finish();
    }
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(INTENT_FILTER1)){
                ArrayList<String>keys1 = intent.getStringArrayListExtra(INTENT_KEY1);
                ArrayList<String>values1 = intent.getStringArrayListExtra(INTENT_KEY2);
                keys =keys1;
                values =values1;
                final List<String> temp = new ArrayList<>();
                for(int i = 0 ; i < keys1.size(); i++){
                    String info = keys1.get(i)+": "+values1.get(i);
                    temp.add(info);
                }

                adapter1 = new ArrayAdapter<String>(that.getBaseContext(),android.R.layout.simple_list_item_1, temp);

                listView.setAdapter(adapter1);

            }
        }
    };

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
