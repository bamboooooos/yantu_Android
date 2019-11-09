package com.example.lin9080.yantu_lin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.function.ToDoubleBiFunction;

public class ChangePasswordActivity extends AppCompatActivity {

    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ActivityCollector.addActivity(this);
        userid=getIntent().getStringExtra("userid");
        UIinit();
    }
    public void UIinit(){
        Toolbar toolbar=(Toolbar)findViewById(R.id.changepasswordtoolbar);
        setSupportActionBar(toolbar);
        ((Button)findViewById(R.id.savePasswordButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String before=((EditText)findViewById(R.id.beforePasswordEdit)).getText().toString();
                String now=((EditText)findViewById(R.id.nowPasswordEdit)).getText().toString();
                String again=((EditText)findViewById(R.id.againPasswordEdit)).getText().toString();
                if(!testPassword(userid,before)){
                    Toast.makeText(ChangePasswordActivity.this,"之前密码输入错误",Toast.LENGTH_SHORT).show();
                }else{
                    if(!now.equals(again)){
                        Toast.makeText(ChangePasswordActivity.this,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
                    }else{
                        if(changePasswordToServer(now)){
                            DataCleanManager.cleanApplicationData(getApplication());
                            restartApp();
                        }else{
                            //TODO 修改密码到服务器失败，需重新发送或者取消处理
                        }

                    }
                }
            }
        });
    }

    public boolean testPassword(String userid,String password){
        //TODO 和服务器密码对比
        return true;
    }

    public boolean changePasswordToServer(String password){
        //TODO 修改密码到服务器
        return true;
    }

    public void restartApp() {
        //重启程序
        Intent intent = new Intent(ChangePasswordActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ActivityCollector.finishAll();
        startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
