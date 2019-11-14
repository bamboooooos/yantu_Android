package com.example.lin9080.yantu_lin;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Date;


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
        //TODO 获取资料并添加到适配器
        //例子
        mlist.addAll(initInfor());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        DTinit();
    }

    ArrayList<Information> initInfor(){
        ArrayList<Information> result=new ArrayList<>();
        Date date=new Date();
        result.add(new Information("1","标题",date.getTime()+"","详情\n详情\n详情\n详情\n详情\n详情\n"));
        result.add(new Information("1","标题",date.getTime()+"","详情\n详情\n详情\n详情\n详情\n详情\n"));
        result.add(new Information("1","标题",date.getTime()+"","详情\n详情\n详情\n详情\n详情\n详情\n"));
        result.add(new Information("1","标题",date.getTime()+"","详情\n详情\n详情\n详情\n详情\n详情\n"));
        result.add(new Information("1","标题",date.getTime()+"","详情\n详情\n详情\n详情\n详情\n详情\n"));
        result.add(new Information("1","标题",date.getTime()+"","详情\n详情\n详情\n详情\n详情\n详情\n"));
        result.add(new Information("1","标题",date.getTime()+"","详情\n详情\n详情\n详情\n详情\n详情\n"));
        result.add(new Information("1","标题",date.getTime()+"","详情\n详情\n详情\n详情\n详情\n详情\n"));
        return result;
    }

}
