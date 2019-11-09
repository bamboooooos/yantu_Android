package com.example.lin9080.yantu_lin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PersonalActivity extends AppCompatActivity {

    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        ActivityCollector.addActivity(this);
        Toolbar toolbar=(Toolbar) findViewById(R.id.personalToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        userid=getIntent().getStringExtra("Userid");
        init(userid);
    }

    public void init(String uid){
        //TODO 使用用户id加载界面
        ((ImageButton)findViewById(R.id.headImageChangeButton)).setEnabled(false);
        ((ImageButton)findViewById(R.id.headImageChangeButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 在此做更改头像的逻辑并传到服务器
            }
        });
        ((Button)findViewById(R.id.changePasswordButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PersonalActivity.this,ChangePasswordActivity.class);
                intent.putExtra("userid",userid);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.personal_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.personalChangeButton:
                changeUIEnable(true);
                break;
            case R.id.personalSaveButton:
                changeUIEnable(false);
                savePersonal(userid);
                saveToServer(userid);
                clearEdit();
                break;
            case android.R.id.home:
                onBackPressed();
                break;
                default:
        }
        return true;
    }

    public void changeUIEnable(boolean isChange){//true为修改时,false不能修改
        int changeVis;
        int nochangeVis;
        if(isChange){
            changeVis= View.VISIBLE;
            nochangeVis=View.GONE;
        }else{
            nochangeVis= View.VISIBLE;
            changeVis=View.GONE;
        }
        findViewById(R.id.usernameChangeText).setVisibility(nochangeVis);
        findViewById(R.id.usernameChangeEdit).setVisibility(changeVis);
        findViewById(R.id.yearChangeText).setVisibility(nochangeVis);
        findViewById(R.id.yearChangeEdit).setVisibility(changeVis);
        findViewById(R.id.majorChangeText).setVisibility(nochangeVis);
        findViewById(R.id.majorChangeEdit).setVisibility(changeVis);
        findViewById(R.id.academyChangeText).setVisibility(nochangeVis);
        findViewById(R.id.academyChangeEdit).setVisibility(changeVis);
        findViewById(R.id.subjectChangeText).setVisibility(nochangeVis);
        findViewById(R.id.subjectChangeEdit).setVisibility(changeVis);
        findViewById(R.id.headImageChangeButton).setEnabled(isChange);
        findViewById(R.id.changePasswordButton).setVisibility(nochangeVis);

    }

    public void savePersonal(String userid){
        saveToServer(userid);
        ((TextView)findViewById(R.id.usernameChangeText)).setText(((EditText)findViewById(R.id.usernameChangeEdit)).getText().toString()+"("+userid+")");
        ((TextView)findViewById(R.id.yearChangeText)).setText(((EditText)findViewById(R.id.yearChangeEdit)).getText().toString());
        ((TextView)findViewById(R.id.majorChangeText)).setText(((EditText)findViewById(R.id.majorChangeEdit)).getText().toString());
        ((TextView)findViewById(R.id.academyChangeText)).setText(((EditText)findViewById(R.id.academyChangeEdit)).getText().toString());
        ((TextView)findViewById(R.id.subjectChangeText)).setText(((EditText)findViewById(R.id.subjectChangeEdit)).getText().toString());
    }

    public void clearEdit(){
        ((EditText)findViewById(R.id.usernameChangeEdit)).setText("");
        ((EditText)findViewById(R.id.yearChangeEdit)).setText("");
        ((EditText)findViewById(R.id.majorChangeEdit)).setText("");
        ((EditText)findViewById(R.id.academyChangeEdit)).setText("");
        ((EditText)findViewById(R.id.subjectChangeEdit)).setText("");
    }

    public void saveToServer(String userid) {
        //TODO 将信息保存到服务器
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
