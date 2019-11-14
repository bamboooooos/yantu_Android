package com.example.lin9080.yantu_lin;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main_2 extends Fragment {
    private List<MyTiezi> tieziList = new ArrayList<>();
    final MyTieziAdapter adapter = new MyTieziAdapter(tieziList);

    public static Main_2 newInstance() {
        return new Main_2();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_2_fragment,container,false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.ShequRecyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        parseJSONWithGSON(init());
        adapter.notifyDataSetChanged();
        return view;
    }

    public String init(){
        String s = "[{\"title\": \"DOG\",\"id\": 123,\"content\": \"清华\",\"zan\": 10,\"pl\":5},{\"name\": \"asd\",\"id\": 456,\"content\": \"北大\",\"zan\": 15,\"pl\":7}]";
        return s;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    private String get(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        String responseData = response.body().string();
        return responseData;
    }

    private void parseJSONWithGSON(String s){
        Gson gson = new Gson();
        List<MyTiezi> list = gson.fromJson(s,new TypeToken<List<MyTiezi>>(){}.getType());
        tieziList.addAll(list);
    }
}