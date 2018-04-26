package com.example.user.carapplication.thread;

import android.content.Context;
import android.util.Log;

import com.example.user.carapplication.controller.CarController;

import java.io.IOException;

/**
 * Created by User on 2018-04-24.
 */

public class AddCarInfoTask implements Runnable{
    private Context context;
    private String vin, name,price;
    public AddCarInfoTask(Context context,String vin, String name, String price) {
        this.context = context;
        this.vin = vin;
        this.name = name;
        this.price = price;
    }

    @Override
    public void run() {
        long result=0;
        long result2;
        try {
            result =new CarController(context).addCarVin(vin, name);
            result2 = new CarController(context).addCarInfo(vin, "price", price);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("result", result+", ");
    }
}
