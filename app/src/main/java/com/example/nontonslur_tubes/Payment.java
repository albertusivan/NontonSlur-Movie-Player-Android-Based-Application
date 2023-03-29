package com.example.nontonslur_tubes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Payment extends AppCompatActivity {

    private Button bronze;
    private Button silver;
    private Button gold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        String username = getIntent().getStringExtra("username");

        bronze = (Button) findViewById(R.id.confirm_button1);
        bronze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Payment.this, selectpayment.class);
                intent.putExtra("duration", "1");
                intent.putExtra("status", "Bronze");
                intent.putExtra("harga", "Rp.3000");
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        silver = (Button) findViewById(R.id.confirm_button2);
        silver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Payment.this, selectpayment.class);
                intent.putExtra("duration", "7");
                intent.putExtra("status", "Silver");
                intent.putExtra("harga", "Rp.15000");
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        gold = (Button) findViewById(R.id.confirm_button3);
        gold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Payment.this, selectpayment.class);
                intent.putExtra("duration", "30");
                intent.putExtra("status", "Gold");
                intent.putExtra("harga", "Rp.50000");
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }

}