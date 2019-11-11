package com.example.lin9080.yantu_lin;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {
    private List<Plan> mList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View planView;
        TextView planText;
        TextView timeText;

        public ViewHolder(View view){
            super(view);
            planView = view;
            timeText = (TextView) view.findViewById(R.id.time_text);
            planText = (TextView) view.findViewById(R.id.plan_text);
        }
    }

    public PlanAdapter(List<Plan> planList){
        mList = planList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.planView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                Plan plan = mList.get(pos);
                Intent intent = new Intent(view.getContext(),PlanAddActivity.class);
                intent.putExtra("id",String.valueOf(plan.getId()));
                Log.d("kxf", String.valueOf(plan.getId()));
                view.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Plan plan = mList.get(i);
        String[] strs=plan.getPlan().split("\\n");
        String str="";
        if (strs.length>3) {
            for (int j = 0; j < 2; j++) {
                str += (strs[j]+"\n");
            }
            str+="...";
        }else{
            for(int j=0;j<strs.length;j++){
                str+=strs[j]+"\n";
            }
        }
        viewHolder.planText.setText(str);
        if (plan.getCalendar().equals("点击左边单选框即可设置提醒时间")) {
            viewHolder.timeText.setText("未设置时间");
        } else {
            viewHolder.timeText.setText(plan.getCalendar());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}