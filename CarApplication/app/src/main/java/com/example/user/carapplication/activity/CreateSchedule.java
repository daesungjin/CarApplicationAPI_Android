package com.example.user.carapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.user.carapplication.R;

public class CreateSchedule extends AppCompatActivity  {
    private Spinner spinner=null;
    private Spinner spinner2=null;
    private Spinner spinner3=null;
    private Spinner spinner4=null;
    private Spinner spinner5=null;
    private Spinner spinner6=null;
    private Spinner spinner7=null;
    private Spinner spinner8=null;
    private Spinner spinner9=null;
    private Spinner spinner10=null;
    private TextView textView=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner4 = (Spinner) findViewById(R.id.spinner4);
        spinner5 = (Spinner) findViewById(R.id.spinner5);
        spinner6 = (Spinner) findViewById(R.id.spinner6);
        spinner7 = (Spinner) findViewById(R.id.spinner7);
        spinner8 = (Spinner) findViewById(R.id.spinner8);
        spinner9 = (Spinner) findViewById(R.id.spinner9);
        spinner10 = (Spinner) findViewById(R.id.spinner10);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                 R.array.year, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.month, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.day, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.hour, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,
                R.array.minute, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this,
                R.array.year, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this,
                R.array.month, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this,
                R.array.day, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(this,
                R.array.hour, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter10 = ArrayAdapter.createFromResource(this,
                R.array.minute, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);
        spinner4.setAdapter(adapter4);
        spinner5.setAdapter(adapter5);
        spinner6.setAdapter(adapter);
        spinner7.setAdapter(adapter2);
        spinner8.setAdapter(adapter3);
        spinner9.setAdapter(adapter4);
        spinner10.setAdapter(adapter5);

    }
    public void create(View v){
        String year = spinner.getSelectedItem().toString();
        String month = spinner2.getSelectedItem().toString();
        String day = spinner3.getSelectedItem().toString();
        String hour = spinner4.getSelectedItem().toString();
        String minute = spinner5.getSelectedItem().toString();
        String year2 = spinner6.getSelectedItem().toString();
        String month2 = spinner7.getSelectedItem().toString();
        String day2 = spinner8.getSelectedItem().toString();
        String hour2 = spinner9.getSelectedItem().toString();
        String minute2 = spinner10.getSelectedItem().toString();
        String time = year+month+day+hour+minute;
        String time2 = year2+month2+day2+hour2+minute2;
        textView = findViewById(R.id.textView3);
        if(Scheduler.scheduleDatabase.insertdata("ds1107", time,time2))
        textView.setText(time+", "+time2);

    }

}
