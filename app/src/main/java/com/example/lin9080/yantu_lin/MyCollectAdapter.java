package com.example.lin9080.yantu_lin;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyCollectAdapter extends RecyclerView.Adapter<MyCollectAdapter.ViewHolder> {
    private List<MyCollect> mList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View myCollectView;
        TextView myCollect_content;
        TextView myCollect_title;

        public ViewHolder(View view){
            super(view);
            myCollectView = view;
            myCollect_content = (TextView) view.findViewById(R.id.myCollect_content);
            myCollect_title = (TextView) view.findViewById(R.id.myCollect_tittle);
        }
    }

    public MyCollectAdapter(List<MyCollect> myCollectList){
        mList = myCollectList;
    }

    @NonNull
    @Override
    public MyCollectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mytiezi_item,parent,false);
        final MyCollectAdapter.ViewHolder holder = new MyCollectAdapter.ViewHolder(view);
        holder.myCollectView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                MyCollect myCollect = mList.get(pos);
                Intent intent = new Intent(view.getContext(),MainActivity.class);
                intent.putExtra("id",String.valueOf(myCollect.getId()));
                view.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCollectAdapter.ViewHolder viewHolder, int i) {
        MyCollect myCollect = mList.get(i);
        viewHolder.myCollect_title.setText(myCollect.getTitle());
        viewHolder.myCollect_content.setText(myCollect.getContent());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}