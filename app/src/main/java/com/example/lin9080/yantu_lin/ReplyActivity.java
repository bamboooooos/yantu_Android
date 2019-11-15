package com.example.lin9080.yantu_lin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ReplyActivity extends AppCompatActivity {
    String url1 = "http://106.54.95.234:8080/tie";
    String url2 = "http://106.54.95.234:8080/tie/findreplys";
    private String id;
    private MyTiezi tiezi;
    private List<Reply> replyList= new ArrayList<>();
    private ReplyAdapter adapter;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        ActivityCollector.addActivity(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        recyclerView = (RecyclerView) findViewById(R.id.reply_recycler);
        Toolbar toolbar=(Toolbar)findViewById(R.id.reply_Toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        adapter = new ReplyAdapter(replyList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        textView1 = (TextView) findViewById(R.id.Tiezi_tittle);
        textView2 = (TextView) findViewById(R.id.Tiezi_content);
        textView3 = (TextView) findViewById(R.id.Tiezi_zan);
        get1(url1);
        get2(url2);
    }

    public void get1(String url){
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("id",id)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                Log.d("linre", str);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        parseJSONWithGSON1(str);
                        textView1.setText(tiezi.getTitle());
                        textView2.setText(tiezi.getContent());
                        textView3.setText("来自："+tiezi.getUser_id());
                    }
                });
            }
        });
    }

    public void get2(String url){
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("tid",id)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("linresult", "Failure:");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String str = response.body().string();
                Log.d("linrr", str);
                Log.d("linrr",id);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        parseJSONWithGSON2(str);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private void parseJSONWithGSON1(String s){
        Gson gson = new Gson();
        tiezi = gson.fromJson(s,new TypeToken<MyTiezi>(){}.getType());
    }

    private void parseJSONWithGSON2(String s){
        Gson gson = new Gson();
        List<Reply> list = gson.fromJson(s,new TypeToken<List<Reply>>(){}.getType());
        replyList.addAll(list);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
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