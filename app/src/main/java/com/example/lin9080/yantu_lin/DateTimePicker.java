package com.example.lin9080.yantu_lin;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;

import java.util.Calendar;

public  class  DateTimePicker implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener{

    private static  Context context;
    private static DatePickerDialog datePickerDialog;
    private static TimePickerDialog pickerDialog;
    private static Calendar calendar;
    private static TextView textView;
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void init(Context context1, TextView editText1)
    {
        calendar=Calendar.getInstance();
        textView=editText1;
        context=context1;
        datePickerDialog=new DatePickerDialog(context,this, calendar.get(Calendar.YEAR), calendar
                .get(Calendar.MONTH), calendar
                .get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        textView.setText( String.valueOf(new StringBuilder()
                .append(year)
                .append("-")
                .append((month + 1) < 10 ? "0"
                        + (month + 1) : (month + 1))
                .append("-")
                .append((day < 10) ? "0" + day : day)));
        initTimePicker(context);
    }

    public void initTimePicker(Context context1){
        context=context1;
        pickerDialog=new TimePickerDialog(context,this,8,00,true);
        pickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String s=(hourOfDay<10?"0"+hourOfDay:hourOfDay)+":"+(minute<10?"0"+minute:minute);
        textView.setText(textView.getText().toString()+" "+s);
    }
}