package com.example.user.carapplication.thread;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.user.carapplication.controller.CarController;
import com.example.user.carapplication.activity.CarInventory;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 2018-04-22.
 */

public class CarListReaderTask implements Runnable {
    private Context context;

    public CarListReaderTask(Context context){
        this.context = context;
    }
    @Override
    public void run() {
        CarController carController = new CarController(context);
        ArrayList<String> list1 = null;
        try {
            list1 = (ArrayList<String>) carController.getCarNameList();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<String> list2 = null;
        try {
            list2 = (ArrayList<String>) carController.getCarVinList();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(list2==null)
        Log.d("OD","list2 is null");
        Intent intent = new Intent(CarInventory.INTENT_FILTER);
        intent.putStringArrayListExtra("carName", list1);
        intent.putStringArrayListExtra("vin", list2);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
