package com.example.lin9080.yantu_lin;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.lin9080.yantu_lin.GsonClass.GsonZixun;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class main_3_fun_2_Fragment extends Fragment {


    public main_3_fun_2_Fragment() {
        // Required empty public constructor
    }



    ArrayList<Information> mlist=new ArrayList<>();
    View view;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    final InforAdapter adapter = new InforAdapter(mlist);
    String result="";
    String url="http://192.168.31.234:8080/data/zx";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_main_3_fun_2_, container, false);
        UIinit();
        return view;
    }
    void UIinit(){
        recyclerView=view.findViewById(R.id.inforRecyclerView);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        DTinit();
    }

    void DTinit(){
        mlist.clear();
                    result="";
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url).method("GET",null).build();
                    Call call = client.newCall(request);
                        call.enqueue(new Callback() {
                        //请求失败执行的方法
                        @Override
                        public void onFailure(Call call, IOException e) {
                        }
                        //请求成功执行的方法
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String data = response.body().string();
                            Log.d("response",data);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    result+=data;
                                    mlist.addAll(parseJSONWithGSON(result));
                                    adapter.notifyDataSetChanged();
                                }
                            });
                        }
                    });
    }

    @Override
    public void onResume() {
        super.onResume();
        DTinit();
    }


    private ArrayList<Information> parseJSONWithGSON(String s){
        Gson gson = new Gson();
        ArrayList<GsonZixun> zixunlist = gson.fromJson(s,new TypeToken<ArrayList<GsonZixun>>(){}.getType());
        ArrayList<Information> result=new ArrayList<>();
        for(int i=0;i<zixunlist.size();i++){
            GsonZixun g=zixunlist.get(i);
            result.add(new Information(g.getId()+"",g.getZb(),g.getZs(),g.getZn()));
        }
        return result;
    }


}
