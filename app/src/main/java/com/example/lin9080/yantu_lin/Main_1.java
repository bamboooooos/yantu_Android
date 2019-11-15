package com.example.lin9080.yantu_lin;


import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Main_1 extends Fragment {

    private List<Plan> planList = new ArrayList<>();
    private PlanHelper dbHelper;
    final PlanAdapter adapter = new PlanAdapter(planList);
    View view;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    SQLiteDatabase db;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.main_1_fragment, container, false);
        Log.d("linres", HomeActivity.userid);
        //TODO 这里获取用户id
        UIinit();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    void UIinit(/*String userid*/){
        //TODO 倒计时

        try {
            Date date=new Date();
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-24");
            long timeCount = date1.getTime() - date.getTime();
            ((TextView) view.findViewById(R.id.countdownDay)).setText((int) (timeCount / 1000 / 60 / 60 / 24)+1 + "");
        }catch (Exception e){
            e.printStackTrace();
        }
        ((ImageButton)view.findViewById(R.id.addPlanButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),PlanAddActivity.class);
                intent.putExtra("id","");
                intent.putExtra("userid","");
                startActivity(intent);
            }
        });
        recyclerView=view.findViewById(R.id.PlanRecyclerView);
        layoutManager=new LinearLayoutManager(getContext());
        dbHelper = new PlanHelper(getContext(),"Plans.db",null,1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        DBinit();
    }

    void DBinit(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Plans",null,null,null,null,null,null);
        planList.clear();
        if(cursor.moveToFirst()){
            do{
                Plan plan = new Plan();
                plan.setId(Integer.valueOf(cursor.getString(cursor.getColumnIndex("id"))));
                plan.setPlan(cursor.getString(cursor.getColumnIndex("plans")));
                plan.setCalendar(cursor.getString(cursor.getColumnIndex("calendar")));
                if(cursor.getString(cursor.getColumnIndex("userid")).equals(HomeActivity.userid))
                planList.add(plan);
            }while (cursor.moveToNext());
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        DBinit();
        super.onResume();
    }
}