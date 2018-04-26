package com.example.user.carapplication.thread;

import android.content.Context;
import android.util.Log;

import com.example.user.carapplication.controller.CarController;

import java.io.IOException;

/**
 * Created by User on 2018-04-26.
 */

public class AddMoreInfoTask implements  Runnable {
    private Context context;
    private String vin, category,info;
    public AddMoreInfoTask(Context context,String vin, String category, String info) {
        this.context = context;
        this.vin = vin;
        this.category = category;
        this.info = info;
    }

    @Override
    public void run() {
        long result=0;
        long result2;
        try {

            result2 = new CarController(context).addCarInfo(vin, category, info);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
