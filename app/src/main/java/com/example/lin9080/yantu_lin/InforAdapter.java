package com.example.lin9080.yantu_lin;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class InforAdapter extends RecyclerView.Adapter<InforAdapter.ViewHolder>{
    private List<Information> mList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View InformationView;
        TextView InforTitleText;
        TextView InforTimeText;
        TextView InforIntroText;

        public ViewHolder(View view){
            super(view);
            InformationView = view;
            InforTitleText =(TextView) view.findViewById(R.id.inforTitle);
            InforTimeText = (TextView) view.findViewById(R.id.inforTime);
            InforIntroText = (TextView) view.findViewById(R.id.inforIntro);
        }
    }

    public InforAdapter(List<Information> list){
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.information_item,parent,false);
        final InforAdapter.ViewHolder holder = new InforAdapter.ViewHolder(view);
        holder.InformationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击事件
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull InforAdapter.ViewHolder viewHolder, int i) {
        Information infor = mList.get(i);
        viewHolder.InforTitleText.setText(infor.getTitle());
        viewHolder.InforTimeText.setText(infor.getTime());
        viewHolder.InforIntroText.setText(infor.getDetail());//TODO 截取片段
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
