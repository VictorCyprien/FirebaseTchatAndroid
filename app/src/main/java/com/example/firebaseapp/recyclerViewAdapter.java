package com.example.firebaseapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import MessageChat.MessageChat;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<MessageChat> list;

    FirebaseAuth auth;
    FirebaseUser user;

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

        //auth = FirebaseAuth.getInstance();
        //user = auth.getCurrentUser();

        //try{
        //    URL req = new URL(
        //            "http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png"
        //    );
        //    Bitmap mIcon_val = BitmapFactory.decodeStream(req.openConnection()
        //            .getInputStream());holder.profil_picture.setImageBitmap(mIcon_val);
        //} catch (MalformedURLException e) {
        //    e.printStackTrace();
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username, message, date;
        ImageView profil_picture;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            message = itemView.findViewById(R.id.message);
            date = itemView.findViewById(R.id.date);
            profil_picture = itemView.findViewById(R.id.profil_picture);
        }
    }
}
