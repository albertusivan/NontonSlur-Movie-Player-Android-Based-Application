package com.example.nontonslur_tubes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nontonslur_tubes.ui.Menu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://nontonslur-tubes-default-rtdb.firebaseio.com");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        final TextView signupbtn = findViewById(R.id.signupbtn);
        final Button loginbtn = findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String usernametxt = username.getText().toString();
                final String passwordtxt = password.getText().toString();

                //checking if user fill all the edittext
                if(usernametxt.isEmpty()){
                    username.setError("Enter Username");
                }
                if(passwordtxt.isEmpty()){
                    password.setError("Enter Password");
                }
                //check if username is already register or not
                else{
                    databaseReference.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(usernametxt)){
                                //validate password
                                final String getpassword = snapshot.child(usernametxt).child("Password").getValue(String.class);

                                if(getpassword.equals(passwordtxt)){
                                    Intent intent = new Intent(login.this, Menu.class);
                                    intent.putExtra("username", usernametxt);
                                    startActivity(intent);
                                }
                                else{
                                    password.setError("Wrong Password");

                                }
                            }
                            //if username not registered yet
                            else{
                                username.setError("User not registered");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, signup.class);
                startActivity(intent);
            }
        });
    }
}