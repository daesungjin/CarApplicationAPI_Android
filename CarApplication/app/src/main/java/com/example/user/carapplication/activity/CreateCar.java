package com.example.user.carapplication.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.carapplication.R;
import com.example.user.carapplication.controller.CarController;
import com.example.user.carapplication.thread.AddCarInfoTask;
import com.example.user.carapplication.thread.CarListReaderTask;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateCar extends AppCompatActivity {
    private ExecutorService es = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_car);


    }

    public void create(View v) throws IOException {
        EditText editText1 = findViewById(R.id.editText3);
        EditText editText2 = findViewById(R.id.editText4);
        EditText editText3 = findViewById(R.id.editText5);
        es = Executors.newSingleThreadExecutor();
        es.execute(new AddCarInfoTask(getApplicationContext(), editText1.getText().toString(), editText2.getText().toString(), editText3.getText().toString()));
        Intent intent = new Intent(this, CarInventory.class);
        showMessage("Create", "Successfully created!");
        startActivity(intent);
        finish();
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
