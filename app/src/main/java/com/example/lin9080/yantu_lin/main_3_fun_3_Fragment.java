package com.example.lin9080.yantu_lin;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class main_3_fun_3_Fragment extends Fragment {


    public main_3_fun_3_Fragment() {
        // Required empty public constructor
    }


    ArrayList<Book> mlist=new ArrayList<>();
    View view;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    final BookAdapter adapter = new BookAdapter(mlist);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_main_3_fun_3_, container, false);
        UIinit();
        return view;
    }
    void UIinit(){
        ((ImageButton)view.findViewById(R.id.findBookButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 搜索
            }
        });
        recyclerView=view.findViewById(R.id.findBookRecyclerView);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        DTinit();
    }

    void DTinit(){
        mlist.clear();
        //TODO 获取资料并添加到适配器
        //这里做几个例子
        mlist.addAll(booksinit());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        DTinit();
    }

    ArrayList<Book> booksinit(){
        ArrayList<Book> result=new ArrayList<>();
        result.add(new Book("1","bilibili","https://www.bilibili.com/video/av64383875"));
        result.add(new Book("2","百度","https://www.baidu.com"));
        result.add(new Book("3","百度网盘","https://pan.baidu.com/s/1-pCjpENgAyacY7rBg5d3mg"));
        result.add(new Book("4","淘宝","https://detail.tmall.com/item.htm?spm=a230r.1.14.1.39c864a33RjD2i&id=533895448017&cm_id=140105335569ed55e27b&abbucket=13"));
        return result;
    }
}
