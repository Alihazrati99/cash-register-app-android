package com.example.cashregisterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    TextView name, price, quantity, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        quantity = findViewById(R.id.quantity);
        date = findViewById(R.id.date);

        Intent i = getIntent();
        if(i.getStringExtra("name") != null){
            name.setText(i.getStringExtra("name"));
            price.setText(i.getStringExtra("price"));
            quantity.setText(i.getStringExtra("quantity"));
            date.setText(i.getStringExtra("date"));
        }
    }
}