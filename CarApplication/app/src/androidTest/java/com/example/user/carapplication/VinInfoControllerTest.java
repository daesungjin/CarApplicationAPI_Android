package com.example.user.carapplication;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.user.carapplication.controller.VinInfoController;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by User on 2018-04-26.
 */
@RunWith(AndroidJUnit4.class)
public class VinInfoControllerTest {
    VinInfoController vinInfoController = null;
    public VinInfoControllerTest(){
        vinInfoController = new VinInfoController(InstrumentationRegistry.getTargetContext());
    }
    @Test
    public void read() throws Exception {
        int i = vinInfoController.call_me("JN8AR05Y5VW103321").size();
        Assert.assertEquals(true, i!=0);
    }
    @Test
    public void read2() throws Exception {
        int i = vinInfoController.call_me("JN8AR05Y5VW103321").size();
        Assert.assertEquals(true, i!=0);
    }

}
