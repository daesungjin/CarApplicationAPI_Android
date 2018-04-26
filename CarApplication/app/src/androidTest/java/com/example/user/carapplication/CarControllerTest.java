package com.example.user.carapplication;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.user.carapplication.controller.CarController;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

/**
 * Created by User on 2018-04-26.
 */
@RunWith(AndroidJUnit4.class)
public class CarControllerTest {
    CarController carController = null;
    public CarControllerTest(){
        carController = new CarController(InstrumentationRegistry.getTargetContext());
    }
    @Test
    public void deleteCar() throws IOException, JSONException {
        int i = carController.getCarVinList().size();
        carController.delete("JN8AR05Y5VW103321");
        carController.deleteCarInfo("JN8AR05Y5VW103321","price");
        int j= carController.getCarVinList().size();
        Assert.assertEquals(1,i-j);
    }
    @Test
    public void createCar() throws IOException, JSONException {
        int i = carController.getCarVinList().size();
        carController.addCarVin("JN8AR05Y5VW103321","Honda");
        carController.addCarInfo("JN8AR05Y5VW103321","price", "5900");
        int j= carController.getCarVinList().size();
        Log.d("i-j", (i-j)+"");
        Assert.assertEquals(1,j-i);
    }
    @Test
    public void makeCar() throws IOException, JSONException {
        int i = carController.getCarVinList().size();
        carController.addCarVin("KMHWF35V11A433252","Honda");
        carController.addCarInfo("KMHWF35V11A433252","non", "5900");
        int j= carController.getCarVinList().size();

        Assert.assertEquals(1,j-i);
    }
    @Test
    public void eCar() throws IOException, JSONException {
        int i = carController.getCarVinList().size();
        carController.delete("KMHWF35V11A433252");
        carController.deleteCarInfo("KMHWF35V11A433252","non");
        int j= carController.getCarVinList().size();
        Assert.assertEquals(1,i-j);
    }

}
