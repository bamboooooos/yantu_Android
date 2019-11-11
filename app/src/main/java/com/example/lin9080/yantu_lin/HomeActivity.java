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

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ActivityCollector.addActivity(this);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=(DrawerLayout)findViewById(R.id.main_drawerLayout);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_account_circle_black_24dp);
        }
        //滑动界面初始化
        drawerInit();
        /*
        String userid=getIntent().getStringExtra("userid");
        //TODO 根据userid初始化界面
        if(!userid.equals("")){

        }*/
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