package com.example.lin9080.yantu_lin;


import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class main_3_fun_1_Fragment extends Fragment {
    private SearchView searchView;
    private TextView textView_name,textView_content;
    private LineChart lineChart;
    private ArrayList<String> labels=new ArrayList<>();
    public main_3_fun_1_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_3_fun_1_, container, false);
        searchView = (SearchView) view.findViewById(R.id.main3_fun_1_searchView);
        textView_name = (TextView) view.findViewById(R.id.main3_fun_1_name);
        textView_content = (TextView) view.findViewById(R.id.main3_fun_1_content);
        lineChart = (LineChart) view.findViewById(R.id.main3_fun_1_lineChart);
        String s = "{\"name\": \"DOG\",\"id\": 320,\"schoolContent\": \"清华\",\"majorContent\": \"sdfsdf\", \"scoreLine1\": 280,\"scoreLine2\": 320,\"scoreLine3\": 290}";
        School school = parseJSONWithGSON(s);
        ArrayList<ChartBean> chartBeanArrayList = new ArrayList<>();
        ArrayList<ArrayList<ChartBean>> lists = new ArrayList<>();
        chartBeanArrayList.add(new ChartBean(school.getScoreLine1(),"2019"));
        chartBeanArrayList.add(new ChartBean(school.getScoreLine2(),"2018"));
        chartBeanArrayList.add(new ChartBean(school.getScoreLine3(),"2017"));
        lists.add(chartBeanArrayList);
        labels.add("123");
        LineChartUtil.initData(lineChart,lists,new int[]{Color.argb(200,0,255,127)},new int[]{Color.argb(200,0,128,128)},labels);
        return view;
    }



    private School parseJSONWithGSON(String s){
        Gson gson = new Gson();
        School school = gson.fromJson(s,new TypeToken<School>(){}.getType());
        return school;
    }
}