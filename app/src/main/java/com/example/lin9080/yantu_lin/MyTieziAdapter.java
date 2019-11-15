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

public class MyTieziAdapter extends RecyclerView.Adapter<MyTieziAdapter.ViewHolder> {
    private List<MyTiezi> mList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View myTieziView;
        TextView myTiezi_titleText;
        TextView myTiezi_contentText;
        TextView myTiezi_zanText;
        TextView myTiezi_plText;

        public ViewHolder(View view){
            super(view);
            myTieziView = view;
            myTiezi_titleText = (TextView) view.findViewById(R.id.myTiezi_tittle);
            myTiezi_contentText = (TextView) view.findViewById(R.id.myTiezi_content);
            myTiezi_zanText = (TextView) view.findViewById(R.id.myTiezi_zan);
            myTiezi_plText = (TextView) view.findViewById(R.id.myTiezi_pl);
        }
    }

    public MyTieziAdapter(List<MyTiezi> mytieziList){
        mList = mytieziList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mytiezi_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.myTieziView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                MyTiezi myTiezi = mList.get(pos);
                Intent intent = new Intent(view.getContext(),ReplyActivity.class);
                intent.putExtra("id",myTiezi.getId()+"");
                view.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MyTiezi myTiezi = mList.get(i);
        viewHolder.myTiezi_titleText.setText(myTiezi.getTitle());
        viewHolder.myTiezi_contentText.setText(myTiezi.getContent());
        viewHolder.myTiezi_zanText.setText("赞："+myTiezi.getZan());
        viewHolder.myTiezi_plText.setText("来自："+myTiezi.getUser_id());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}