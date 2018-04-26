package com.example.user.carapplication.thread;

import android.content.Context;
import android.util.Log;

import com.example.user.carapplication.controller.CarController;

import java.io.IOException;
import java.util.List;

/**
 * Created by User on 2018-04-25.
 */

public class DeleteCarTask implements  Runnable {
    private String vin;
    private List<String> category;
    private Context context;
    public DeleteCarTask(Context context,String vin, List<String> category) {
        this.vin = vin;
        this.context = context;
        this.category = category;
    }

    @Override
    public void run() {
        long result = 0;
        long result2 = 0;
        try {
            result = new CarController(context).delete(vin);
            for(String key : category)
            {result2 = new CarController(context).deleteCarInfo(vin, key);
            Log.d("result2", key+"");}
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
