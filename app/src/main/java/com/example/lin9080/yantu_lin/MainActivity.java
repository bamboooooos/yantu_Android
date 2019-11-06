package com.example.lin9080.yantu_lin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static int changeFlag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewinit();
    }

    void viewinit(){
        findViewById(R.id.phoneLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(changeFlag==0) {
                    ((TextView)findViewById(R.id.phoneLogin)).setText("密码登录");
                    ((EditText) findViewById(R.id.loginUser)).setHint("手机号码");
                    ((EditText) findViewById(R.id.loginPassword)).setHint("短信验证码");
                    findViewById(R.id.getyzm).setVisibility(View.VISIBLE);
                    findViewById(R.id.forget).setVisibility(View.GONE);
                    ((TextView)findViewById(R.id.loginUser)).setText("");
                    ((TextView)findViewById(R.id.loginPassword)).setText("");
                    changeFlag=1;
                    //TODO 更改图案
                }else{
                    ((TextView)findViewById(R.id.phoneLogin)).setText("快速登录");
                    ((EditText) findViewById(R.id.loginUser)).setHint("手机/邮箱/用户名");
                    ((EditText) findViewById(R.id.loginPassword)).setHint("密码");
                    findViewById(R.id.getyzm).setVisibility(View.GONE);
                    findViewById(R.id.forget).setVisibility(View.VISIBLE);
                    findViewById(R.id.tipText).setVisibility(View.GONE);
                    ((TextView)findViewById(R.id.loginUser)).setText("");
                    ((TextView)findViewById(R.id.loginPassword)).setText("");
                    changeFlag=0;
                    //TODO 更改图案
                }
            }
        });
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 登录
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
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
