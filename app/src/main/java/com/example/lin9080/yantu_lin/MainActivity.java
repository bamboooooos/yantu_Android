package com.example.lin9080.yantu_lin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.sql.Date;
import java.sql.Time;

public class MainActivity extends AppCompatActivity {

    static int changeFlag=0;//0表示在密码界面，1表示在快速登录

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActivityCollector.addActivity(this);
        SharedPreferences preferences=getPreferences(MODE_PRIVATE);
        boolean islogin=preferences.getBoolean("isLogin",false);
        boolean isrem=preferences.getBoolean("isRem",false);
        long loginTime=preferences.getLong("time",0);
        String userid=preferences.getString("userid","");
        /*
        Log.d("Linislogin", islogin+"");
        Log.d("Linisrem", isrem+"");
        Log.d("LinloginTime", loginTime+"");
        Log.d("Linuserid", userid+"");
        */
        Date date=new Date(System.currentTimeMillis());
        long timenow=date.getTime();
        Log.d("Lintimenow", timenow+"");
        //记住登录
        long saveTime;
        if(isrem) {
            saveTime=7*24*60*60*1000;
        }else{
            saveTime=6*60*60*1000;
        }
        if (islogin) {
            if (timenow - loginTime < saveTime) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("userid",userid);
                startActivity(intent);
            } else {
                preferences.edit().putBoolean("islogin", false).apply();
                preferences.edit().putString("userid","").apply();
            }
        }
        viewinit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    void viewinit(){
        findViewById(R.id.phoneLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(changeFlag==0) {
                    changeFlag=1;
                    ((TextView)findViewById(R.id.phoneLogin)).setText("密码登录");
                    ((EditText)findViewById(R.id.loginUser)).setHint("手机号码");
                    ((EditText)findViewById(R.id.loginPassword)).setHint("短信验证码");
                    findViewById(R.id.getyzmImage).setVisibility(View.VISIBLE);
                    findViewById(R.id.getyzm).setVisibility(View.GONE);
                    findViewById(R.id.forget).setVisibility(View.GONE);
                    ((TextView)findViewById(R.id.loginUser)).setText("");
                    ((TextView)findViewById(R.id.loginPassword)).setText("");
                    ((ImageButton)findViewById(R.id.login)).setImageDrawable(getResources().getDrawable(R.drawable.loginfast));
                }else{
                    changeFlag=0;
                    ((TextView)findViewById(R.id.phoneLogin)).setText("快速登录");
                    ((EditText) findViewById(R.id.loginUser)).setHint("手机/邮箱/用户名");
                    ((EditText) findViewById(R.id.loginPassword)).setHint("密码");
                    findViewById(R.id.getyzm).setVisibility(View.GONE);
                    findViewById(R.id.getyzmImage).setVisibility(View.GONE);
                    findViewById(R.id.forget).setVisibility(View.VISIBLE);
                    findViewById(R.id.tipText).setVisibility(View.GONE);
                    ((TextView)findViewById(R.id.loginUser)).setText("");
                    ((TextView)findViewById(R.id.loginPassword)).setText("");
                    ((ImageButton)findViewById(R.id.login)).setImageDrawable(getResources().getDrawable(R.drawable.login));
                }
            }
        });
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 登录
                //登录成功
                SharedPreferences preferences=getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putBoolean("isLogin",true);
                Date date = new Date(System.currentTimeMillis());
                editor.putString("userid",((EditText)findViewById(R.id.loginUser)).getText().toString());//TODO 将此改为由服务器发送的id
                editor.putLong("time",date.getTime());
                if(((CheckBox)findViewById(R.id.remember)).isChecked()){
                    editor.putBoolean("isRem",true);
                }else{
                    editor.putBoolean("isRem",false);
                }
                editor.apply();
                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
        ((EditText)findViewById(R.id.loginUser)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().length() >0){
                    findViewById(R.id.login).setVisibility(View.VISIBLE);
                    if(changeFlag==1){
                        findViewById(R.id.tipText).setVisibility(View.VISIBLE);
                    }
                }else {
                    findViewById(R.id.login).setVisibility(View.GONE);
                    findViewById(R.id.tipText).setVisibility(View.GONE);
                }
                if(changeFlag==1) {
                    if (charSequence.toString().length() == 11) {
                        findViewById(R.id.getyzmImage).setVisibility(View.GONE);
                        findViewById(R.id.getyzm).setVisibility(View.VISIBLE);
                    } else {
                        findViewById(R.id.getyzmImage).setVisibility(View.VISIBLE);
                        findViewById(R.id.getyzm).setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
}
