package com.example.user.carapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.user.carapplication.R;
import com.example.user.carapplication.activity.CarInventory;

import java.util.concurrent.ScheduledExecutorService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void buildInventory(View v){
        Intent intent = new Intent(this, CarInventory.class);
        startActivity(intent);

    }
    public void buildScheduler(View v){
        Intent intent = new Intent(this, Scheduler.class);
        startActivity(intent);

    }
}
