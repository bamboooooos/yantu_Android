package com.example.lin9080.yantu_lin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity {

    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ActivityCollector.addActivity(this);
        userid=getIntent().getStringExtra("userid");
        setSupportActionBar((Toolbar)findViewById(R.id.settingToolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        UIinit();
    }
    public void UIinit(){
        ((Button)findViewById(R.id.logout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataCleanManager.cleanApplicationData(getApplication());
                restartApp();
            }
        });
        //TODO 根据id获取手机号
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    public void restartApp() {
        //重启程序
        Intent intent = new Intent(SettingActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ActivityCollector.finishAll();
        startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
