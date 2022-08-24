package com.example.firebaseapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import MessageChat.MessageChat;


public class MainActivity extends FragmentActivity {

    private Button logout;
    private Button my_profil;

    recyclerViewAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<MessageChat> list;
    TextInputLayout message;
    FloatingActionButton send;
    DatabaseReference db;
    FirebaseAuth auth;
    FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout = findViewById(R.id.logout);
        my_profil = findViewById(R.id.my_profile);

        send = findViewById(R.id.msg_send);
        message = findViewById(R.id.message);
        recyclerView = findViewById(R.id.recyclerview);
        list = new ArrayList<>();

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference();

        user = auth.getCurrentUser();
        String user_id = user.getUid();
        String user_email = user.getEmail();
        String username = user.getDisplayName();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Utilisateur déconnecté !", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, StartActivity.class));
            }
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = message.getEditText().getText().toString();
                if (msg.equals("")){
                    Toast.makeText(MainActivity.this, "Il n'a pas de message à envoyer", Toast.LENGTH_SHORT).show();
                    return;
                }
                TimeZone.setDefault(TimeZone.getTimeZone("Europe/Paris"));
                String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a").format(Calendar.getInstance().getTime());
                db.child("messages").push().setValue(new MessageChat(msg, username, user_email, date, null)).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        message.getEditText().setText("");
                    }
                });
            }
        });

        adapter = new recyclerViewAdapter(this, list);
        LinearLayoutManager llm = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);

        //HashMap<String, Object> msg = new HashMap<>();
        //msg.put("date", "13/12/1999");
        //msg.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail().toString());
        //msg.put("message", "Holà !");
        //msg.put("username", FirebaseAuth.getInstance().getCurrentUser().getDisplayName().toString());

        //FirebaseDatabase.getInstance().getReference().child("messages").push().setValue(msg);
    }

    @Override
    protected void onStart(){
        super.onStart();
        receiveMessage();
    }

    private void receiveMessage(){
        db.child("messages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot snap: snapshot.getChildren()){
                    MessageChat message = snap.getValue(MessageChat.class);
                    list.add(message);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}