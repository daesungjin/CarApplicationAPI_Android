package com.example.user.carapplication;

import android.database.Cursor;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.user.carapplication.db.ScheduleDatabase;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ScheduleDatabaseTest {
    ScheduleDatabase scheduleDatabase = null;
    public ScheduleDatabaseTest(){
        scheduleDatabase = new ScheduleDatabase(InstrumentationRegistry.getTargetContext());
    }
    @Test
    public void create(){
        Cursor cursor = scheduleDatabase.getAllData();
        Assert.assertEquals(false, cursor.getCount()==0);
    }
}
