package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    private Button logout;
    private Button my_profil;
    private ListView messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout = findViewById(R.id.logout);
        my_profil = findViewById(R.id.my_profile);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Utilisateur déconnecté !", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, StartActivity.class));
            }
        });


        //HashMap<String, Object> msg = new HashMap<>();
        //msg.put("date", "13/12/1999");
        //msg.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail().toString());
        //msg.put("message", "Holà !");
        //msg.put("username", FirebaseAuth.getInstance().getCurrentUser().getDisplayName().toString());

        //FirebaseDatabase.getInstance().getReference().child("messages").push().setValue(msg);

    }
}