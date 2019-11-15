package com.example.lin9080.yantu_lin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity {

    Intent intent0;
    static String userid="";
    String address="http://106.54.95.234:8080";
    String url=address+"/register";
    DrawerLayout drawerLayout;
    Login login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        intent0=getIntent();
        userid=intent0.getStringExtra("userid");

        ActivityCollector.addActivity(this);
        drawerLayout=(DrawerLayout)findViewById(R.id.main_drawerLayout);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_account_circle_black_24dp);
        }
        //滑动界面初始化
        drawerInit();
        Boolean status=intent0.getBooleanExtra("status",false);
        if(status){//如果是注册
            final LoginDialog dialog=new LoginDialog(this);
            dialog.show();
            dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            dialog.findViewById(R.id.majorSava).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String editedName = ((EditText) dialog.findViewById(R.id.nameEdit)).getText().toString();
                    if (editedName.length() <= 0) {
                        Toast.makeText(getApplicationContext(), "用户名为空！", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("linresult", "click");
                        login = dialog.getLoginData();
                        login.setPhone(userid);
                        login.setNamel(editedName);
                        login.setPassword(intent0.getStringExtra("password"));
                        OkHttpClient mOkHttpClient = new OkHttpClient();
                        RequestBody formBody = new FormBody.Builder()
                                .add("phone", login.getPhone())
                                .add("password", login.getPassword())
                                .add("name", login.getNamel())
                                .add("major", login.getMajor() + "")
                                .add("years", login.getYear() + "")
                                .build();
                        Request request = new Request.Builder()
                                .url(url)
                                .post(formBody)
                                .build();
                        Call call = mOkHttpClient.newCall(request);
                        call.enqueue(new Callback() {
                            //请求失败执行的方法
                            @Override
                            public void onFailure(Call call, IOException e) {
                            }

                            //请求成功执行的方法
                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                final String data = response.body().string();
                                Log.d("response", data);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                    }
                                });
                            }
                        });
                        dialog.dismiss();
                    }
                }
            });
        }
        //TODO 根据id初始化
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this,R.id.fragment);
        AppBarConfiguration configuration = new AppBarConfiguration.Builder(bottomNavigationView.getMenu()).build();
        //NavigationUI.setupActionBarWithNavController(this,navController,configuration);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

    }

    //返回键到桌面，并不退出
    @Override
    public void onBackPressed() {
        goBackToDesktop();
    }


    private void goBackToDesktop() {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home.addCategory(Intent.CATEGORY_HOME);
        startActivity(home);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
                default:
        }
        return true;
    }
    public void drawerInit(){
        NavigationView navigationView=(NavigationView)  findViewById(R.id.drawerView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.personal:
                        Intent intent1=new Intent(HomeActivity.this,PersonalActivity.class);
                        //TODO 在此传入用户id
                        intent1.putExtra("Userid","");
                        startActivity(intent1);
                        break;
                    case R.id.post:
                        Intent intent2=new Intent(HomeActivity.this,MyTieziActivity.class);
                        //TODO 在此传入用户id
                        intent2.putExtra("Userid","");
                        startActivity(intent2);

                        break;

                    case R.id.collection:
                        Intent intent3=new Intent(HomeActivity.this,MyCollectActivity.class);
                        //TODO 在此传入用户id
                        intent3.putExtra("Userid","");
                        startActivity(intent3);

                        break;
                    case R.id.setting:
                        //TODO 打开设置页面,传入用户id
                        Intent intent4=new Intent(HomeActivity.this,SettingActivity.class);
                        intent4.putExtra("userid","");
                        startActivity(intent4);
                        break;
                        default:
                }
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}