package com.example.lin9080.yantu_lin;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class LoginDialog extends AlertDialog {
    Context context;
    Spinner majorSpinner1;
    Spinner majorSpinner2;
    Spinner schoolSpinner;
    ArrayList<String> list1=new ArrayList<>();
    ArrayList<String> list2=new ArrayList<>();
    ArrayList<String> list3=new ArrayList<>();
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;
    ArrayAdapter<String> adapter3;
    Button button;
    String selectMajor="";
    String selectSchool="";
    String editedName="";

    public LoginDialog(Context context){
        super(context);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_dialog);
        majorSpinner1=(Spinner)findViewById(R.id.major1Select);
        majorSpinner2=(Spinner)findViewById(R.id.major2Select);
        schoolSpinner=(Spinner)findViewById(R.id.schoolSelect);
        button=(Button)findViewById(R.id.majorSava);
        adapter1=new ArrayAdapter<String> (context,android.R.layout.simple_spinner_dropdown_item,list1);
        adapter2=new ArrayAdapter<String> (context,android.R.layout.simple_spinner_dropdown_item,list2);
        adapter3=new ArrayAdapter<String> (context,android.R.layout.simple_spinner_dropdown_item,list3);
        majorSpinner1.setAdapter(adapter1);
        majorSpinner2.setAdapter(adapter2);
        schoolSpinner.setAdapter(adapter3);
        major1dataInit();
        majorSpinner1.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                major2dataInit(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        majorSpinner2.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectMajor=((TextView)view).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        schoolSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectSchool=((TextView)view).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        schooldataInit();
        setCancelable(false);
    }

    public Login getLoginData(){
        return new Login("","","",MajorToID.M2I(selectMajor+selectSchool),2019);
    }

    void major1dataInit(){
        list1.clear();
        list1.add("理学");
        list1.add("工学");
        list1.add("文学");
        adapter1.notifyDataSetChanged();
    }

    void major2dataInit(int mode){
        switch (mode){
            case 0:
                list2.clear();
                list2.add("数学");
                list2.add("物理");
                list2.add("化学");
                list2.add("心理学");
                adapter2.notifyDataSetChanged();
                break;
            case 1:
                list2.clear();
                list2.add("计算机科学与技术");
                list2.add("土木工程");
                list2.add("电气工程");
                list2.add("机械工程");
                adapter2.notifyDataSetChanged();
                break;
            case 2:
                list2.clear();
                list2.add("中国语言文学");
                list2.add("外国语言文学");
                list2.add("新闻传播学");
                adapter2.notifyDataSetChanged();
                default:
        }
    }

    void schooldataInit(){
        list3.clear();
        list3.add("福州大学");
        adapter3.notifyDataSetChanged();
    }


}
