package com.example.lin9080.yantu_lin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;


public class PlanAddActivity extends AppCompatActivity {
    String userid = "031702418";
    private SQLiteDatabase db;
    String id = "";
    DateTimePicker dateTimePicker;
    Toolbar toolbar;
    EditText editText;
    TextView textView;
    CheckBox checkBox;
    private PlanHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_add);
        toolbar = (Toolbar) findViewById(R.id.planAdd_toolbar);
        editText = (EditText) findViewById(R.id.planAdd_editText);
        checkBox = (CheckBox) findViewById(R.id.planAdd_check);
        textView = (TextView) findViewById(R.id.planAdd_textView);
        dateTimePicker = new DateTimePicker();
        toolbar.setTitle("制定计划");

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        if(!id.equals("")){
            dbHelper = new PlanHelper(this,"Plans.db",null,1);
            db = dbHelper.getWritableDatabase();
            Cursor cursor = db.query("Plans",null,"id = ?",new String[]{id},null,null,null);
            cursor.moveToFirst();
            editText.setText(cursor.getString(cursor.getColumnIndex("plans")));
            String calendar = cursor.getString(cursor.getColumnIndex("calendar"));
            if(!calendar.equals("点击左边单选框即可设置提醒时间")){
                textView.setText(calendar);
                checkBox.setChecked(true);
            }
        }

        setSupportActionBar(toolbar);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dateTimePicker.init(PlanAddActivity.this,textView);
                }
                else{
                    textView.setText("点击左边单选框即可设置提醒时间");
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//添加默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.planadd_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.planAddKeep:
                if(id.equals("")){
                    if(editText.getText().toString().equals("")){
                        finish();
                    }else {
                        dbHelper = new PlanHelper(this, "Plans.db", null, 1);
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put("calendar", textView.getText().toString());
                        values.put("plans", editText.getText().toString());
                        db.insert("Plans", null, values);
                        Log.d("kxf", textView.getText().toString());
                        values.clear();
                        finish();
                    }
                }else{
                    dbHelper = new PlanHelper(this,"Plans.db",null,1);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("calendar",textView.getText().toString());
                    values.put("plans",editText.getText().toString());
                    db.update("Plans",values,"id = ?",new String[]{id});
                }
                onBackPressed();
                break;
            case R.id.planAddDelete:
                if(!id.equals("")){
                    Log.d("kxf", id);
                    db.delete("Plans","id = ?",new String[]{id});
                }
                finish();
                break;
            case android.R.id.home:
                onBackPressed();
        }
        return true;
    }
}