package com.example.lin9080.yantu_lin;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReplyAdapter extends RecyclerView.Adapter<ReplyAdapter.ViewHolder>{
    private List<Reply> mList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View replyView;
        TextView replyText;
        TextView replyName;

        public ViewHolder(View view){
            super(view);
            replyView = view;
            replyText = (TextView) view.findViewById(R.id.reply_text);
            replyName = (TextView) view.findViewById(R.id.reply_name);
        }
    }
    public ReplyAdapter(List<Reply> replyList){
        mList = replyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reply_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.replyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                Reply reply = mList.get(pos);
                /*Intent intent = new Intent(view.getContext(),MainActivity.class);
                intent.putExtra("id",String.valueOf(reply.getId()));
                view.getContext().startActivity(intent);*/
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Reply reply = mList.get(i);
        viewHolder.replyText.setText(reply.getPn());
        viewHolder.replyName.setText("来自："+reply.getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}