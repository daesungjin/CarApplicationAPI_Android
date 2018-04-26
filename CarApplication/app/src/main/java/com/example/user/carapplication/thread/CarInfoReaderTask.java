package com.example.user.carapplication.thread;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.example.user.carapplication.activity.CarInfo;
import com.example.user.carapplication.controller.CarController;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 2018-04-22.
 */

public class CarInfoReaderTask implements Runnable{
    private Context context;
    private String vin;
    public CarInfoReaderTask(Context context, String vin) {
        this.context = context;
        this.vin = vin;
    }

    @Override
    public void run() {
        CarController carController = new CarController(context);
        ArrayList<String> list1 = null;
        ArrayList<String> list2 = null;
        try {
            list1 = (ArrayList<String>) carController.getCarInfoKeys(vin);
            list2 = (ArrayList<String>) carController.getCarInfoValues(vin);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(CarInfo.INTENT_FILTER1);
        intent.putStringArrayListExtra(CarInfo.INTENT_KEY1, list1);
        intent.putStringArrayListExtra(CarInfo.INTENT_KEY2, list2);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
