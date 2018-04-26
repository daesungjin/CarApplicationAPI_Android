package com.example.user.carapplication.thread;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.user.carapplication.activity.VinInfo;
import com.example.user.carapplication.controller.VinInfoController;

import java.util.ArrayList;

/**
 * Created by User on 2018-04-25.
 */

public class VinInfoReaderTask implements Runnable{
    private Context context;
    private String vin;

    public VinInfoReaderTask(Context context, String vin) {
        this.context = context;
        this.vin = vin;
    }

    @Override
    public void run() {
        ArrayList<String> arrayList = null;
        try {
            arrayList = new VinInfoController(context).call_me(vin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(VinInfo.INTENT_FILTER);
        Log.d("size", arrayList.size()+"");
        if(arrayList== null) Log.d("message","fail");
        else intent.putStringArrayListExtra(VinInfo.INTENT_KEY,arrayList);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
