package com.example.firebaseapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import MessageChat.MessageChat;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<MessageChat> list;

    public recyclerViewAdapter(Context context, ArrayList<MessageChat> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public recyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.message_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerViewAdapter.ViewHolder holder, int position) {
        holder.username.setText(list.get(position).getUsername());
        holder.message.setText(list.get(position).getMessage());
        holder.date.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username, message, date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            message = itemView.findViewById(R.id.message);
            date = itemView.findViewById(R.id.date);

        }
    }
}
