package com.example.nontonslur_tubes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nontonslur_tubes.ui.Menu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    private Button logout;
    private Button home;
    private Button search;
    private TextView username;
    private TextView status;
    private TextView time;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://nontonslur-tubes-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String usernametxt = getIntent().getStringExtra("username");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username = findViewById(R.id.username);
        status = findViewById(R.id.status);
        time = findViewById(R.id.duration);

        logout = (Button) findViewById(R.id.confirm_button3);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, login.class);
                startActivity(intent);
            }
        });

        home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, Menu.class);
                intent.putExtra("username", usernametxt);
                startActivity(intent);
            }
        });

        search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, Search.class);
                intent.putExtra("username", usernametxt);
                startActivity(intent);
            }
        });

        databaseReference.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String statustxt = snapshot.child(usernametxt).child("Status").getValue(String.class);
                status.setText(statustxt);
                Integer durationtxt = snapshot.child(usernametxt).child("Duration").getValue(Integer.class);
                time.setText((durationtxt + " Days"));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        username.setText(usernametxt);

    }


}