package com.example.nontonslur_tubes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class selectpayment extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://nontonslur-tubes-default-rtdb.firebaseio.com/");

    private Button confirm;
    private TextView harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectpayment);

        String statustxt = getIntent().getStringExtra("status");
        String durationtxt = getIntent().getStringExtra("duration");
        String username = getIntent().getStringExtra("username");
        String hargatxt = getIntent().getStringExtra("harga");

        Integer durationint = Integer.parseInt(durationtxt);

        harga = findViewById(R.id.harga);
        harga.setText(hargatxt);

        confirm = (Button) findViewById(R.id.confirm_button1);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child("User").child(username).child("Status").setValue(statustxt);
                        databaseReference.child("User").child(username).child("Duration").setValue(durationint);

                        Intent intent = new Intent(selectpayment.this, receipt.class);
                        intent.putExtra("username", username);
                        intent.putExtra("harga", hargatxt);
                        intent.putExtra("status", statustxt);
                        startActivity(intent);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }

}