package com.example.lin9080.yantu_lin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MyTieziActivity extends AppCompatActivity {
    Toolbar toolbar;
    MyTieziAdapter adapter;
    List<MyTiezi> myTieziList= new ArrayList<>();

    private void myTieziInit(){
        MyTiezi myTiezi = new MyTiezi();
        myTiezi.setContent("Content");
        myTiezi.setPl(15);
        myTiezi.setId(0);
        myTiezi.setZan(10);
        myTiezi.setTitle("MyTiezi");
        myTieziList.add(myTiezi);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tiezi);
        toolbar = (Toolbar) findViewById(R.id.myTiezi_toolbar);
        toolbar.setTitle("我的贴子");
        setSupportActionBar(toolbar);
        myTieziInit();
        adapter = new MyTieziAdapter(myTieziList);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.myTiezi_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//添加默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
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
}