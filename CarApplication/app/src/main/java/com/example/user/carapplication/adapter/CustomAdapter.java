package com.example.user.carapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.carapplication.R;

import java.util.ArrayList;


/**
 * Created by User on 2018-04-23.
 */

public class CustomAdapter extends ArrayAdapter<String> {

    public CustomAdapter(Context context, ArrayList<String> items) {
        super(context, R.layout.custom_row);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.custom_row,parent,false);
        Log.d("item",""+getItem(position).toString());
        String item = getItem(position);
        TextView textView = (TextView) customView.findViewById(R.id.textView);
        textView.setText("boaab");

        return customView;
    }
}
