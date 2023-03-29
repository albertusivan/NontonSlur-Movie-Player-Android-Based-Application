package com.example.nontonslur_tubes;

import android.annotation.SuppressLint;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class receipt extends AppCompatActivity {

    private Button beranda;
    private TextView tanggal;
    private TextView harga;
    private TextView status;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://nontonslur-tubes-default-rtdb.firebaseio.com/");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        tanggal = findViewById(R.id.tanggal);
        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String date = formatter.format(today);
        tanggal.setText(date);

        String hargatxt = getIntent().getStringExtra("harga");
        String statustxt = getIntent().getStringExtra("status");
        String usernametxt = getIntent().getStringExtra("username");

        harga = findViewById(R.id.harga);
        harga.setText(hargatxt);

        status = findViewById(R.id.rec_status);
        status.setText("Pembelian Paket "+statustxt);

        databaseReference.child("User").addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   databaseReference.child("User").child(usernametxt).child("PaymentDate").setValue(date);
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {}
           });

        beranda = (Button) findViewById(R.id.confirm_button1);
        beranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(receipt.this, Profile.class);
                intent.putExtra("username", usernametxt);
                startActivity(intent);
            }
        });

    }
}

