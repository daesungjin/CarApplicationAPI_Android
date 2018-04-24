package com.example.user.carapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by User on 2018-04-23.
 */

public class ScheduleDatabase  extends SQLiteOpenHelper {
    public static final String DATABASE = "CarApplication.db";
    public static final String TABLE_NAME= "schedule";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "USERID";
    public static final String COL_3 = "STARTdate";
    public static final String COL_4 = "ENDdate";
    public ScheduleDatabase(Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table schedule (ID integer primary key AUTOINCREMENT, USERID varchar(50), STARTdate varchar(50), enddate varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean insertdata(String userid, String startdate, String enddate){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        if(!isAvailable(startdate,enddate)) return false;
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, userid);
        contentValues.put(COL_3,startdate);
        contentValues.put(COL_4,enddate);
        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if(result==-1) return false;
        else return true;
    }
    private boolean isAvailable(String checkinDate, String checkoutDate) {
        ArrayList<Date> checkinList = new ArrayList<>();
        ArrayList<Date> checkoutList = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmm");
        Date checkInDate = null;
        Date checkOutDate = null;
        try {
            checkInDate = simpleDateFormat.parse(checkinDate);
            checkOutDate = simpleDateFormat.parse(checkoutDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Cursor cursor = getAllData();
        if(cursor==null) return true;
        while(cursor.moveToNext()){
            String temp1 = cursor.getString(2);
            String temp2 = cursor.getString(3);
            try {
                checkinList.add(simpleDateFormat.parse(temp1));
                checkoutList.add(simpleDateFormat.parse(temp2));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        for(int i = 0 ; i < checkinList.size(); i++){
            Date ci = checkinList.get(i);
            Date co = checkoutList.get(i);
            if(checkInDate.after(checkOutDate)||(checkInDate.after(ci)&&checkInDate.before(co))||(checkOutDate.after(ci)&&checkOutDate.before(co))||(checkInDate.before(ci)&&checkOutDate.after(co))||checkInDate.equals(ci)||checkOutDate.equals(co)){
                return false;
            }
        }
        return true;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        Log.d(TABLE_NAME, res.getCount()+"");
        return res;
    }
    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
}
