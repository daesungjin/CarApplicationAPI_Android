package com.example.user.carapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.user.carapplication.R;
import com.example.user.carapplication.thread.AddMoreInfoTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InsertInfo extends AppCompatActivity {
    ExecutorService executorService = null;
    EditText editText = null;
    EditText editText1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_info);
        editText = findViewById(R.id.editText);
        editText1 = findViewById(R.id.editText2);

    }
    public void insert(View v){
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new AddMoreInfoTask(this,getIntent().getStringExtra("vin"), editText.getText().toString(),editText1.getText().toString()));
        Intent intent = new Intent(this,CarInfo.class);
        intent.putExtra("vin",getIntent().getStringExtra("vin"));
        startActivity(intent);
        finish();
    }
}
